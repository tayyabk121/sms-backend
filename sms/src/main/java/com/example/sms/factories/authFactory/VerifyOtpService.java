package com.example.sms.factories.authFactory;

import com.example.sms.exception.InvalidAuthenticationRequestException;
import com.example.sms.exception.InvalidOtpException;
import com.example.sms.exception.OtpExpiredException;
import com.example.sms.model.User;
import com.example.sms.repository.UserRepository;
import com.example.sms.request.AuthenticationRequest;
import com.example.sms.response.AuthenticationResponse;
import com.example.sms.security.JwtUtil;
import com.example.sms.util.AuthenticationRequestType;
import com.example.sms.validation.AuthValidation;
import com.example.sms.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * VerifyOtpService is a service class responsible for handling the operation of verifying
 * OTP (One-Time Password) during the authentication process. It implements the
 * AuthenticationOperation interface, which defines the contract for performing operations
 * related to user authentication.
 *
 * The service validates the OTP provided in the authentication request against the OTP
 * stored in Redis, checks for expiration, and upon successful verification, it either
 * retrieves the existing user or creates a new user associated with the phone number.
 * It then generates JWT access and refresh tokens, stores the refresh token in Redis,
 * and returns an authentication response containing the tokens and user information.
 *
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class VerifyOtpService implements AuthenticationOperation{
    
    /** Redis key prefix for storing OTPs associated with phone numbers. */
    private static final String OTP_KEY = "otp:";
    
    /** Redis key prefix for storing refresh tokens associated with user IDs. */
    private static final String REFRESH_TOKEN_KEY = "refresh:";
    
    /** Repository for accessing user data from the database. */
    private final UserRepository userRepository;
    
    /** Validation component for user-related operations. */
    private final UserValidation userValidation;
    
    /** RedisTemplate for interacting with Redis to store and retrieve OTPs,
     * refresh tokens, and rate-limiting data. */
    private final RedisTemplate<String, String> redisTemplate;
    
        /** Validation component for authentication-related operations,
         * such as OTP validation and refresh token validation. */
    private final AuthValidation authValidation;
    
    /** Utility for generating and validating JWT tokens, including
     * access and refresh tokens. */
    private final JwtUtil jwtUtil;
    
    /** Expiry time for refresh tokens in milliseconds, injected from
     * application properties. */
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;
    
    /** Method to get the type of authentication request this service handles, which is VERIFY_OTP in
     * this case. This method is used to identify the type of operation being performed when
     * processing an AuthenticationRequest. */
    @Override
    public AuthenticationRequestType getAuthenticationRequestType() {
        return AuthenticationRequestType.VERIFY_OTP;
    }
    
    /** Method to perform the OTP verification operation based on the provided AuthenticationRequest.
     * This method validates the OTP against the stored value in Redis, checks for expiration,
     * retrieves or creates a User entity, generates JWT tokens, stores the refresh token in Redis,
     * and returns an AuthenticationResponse containing the tokens and user information.
     *
     * @param request The AuthenticationRequest containing the phone number and OTP to be verified.
     * @return An AuthenticationResponse containing the access token, refresh token, user ID, and a success message.
     * @throws OtpExpiredException if the OTP has expired or is not found in Redis.
     * @throws InvalidOtpException if the provided OTP does not match the stored OTP for the phone number.
     */
    @Override
    public AuthenticationResponse performOperation(
            final AuthenticationRequest request) {
        
        if (request.getPhoneNumber() != null) {
            
            return handlePhoneNumberVerifyOtp(request);
            
        } else if (request.getEmail() != null) {
            
            return handleEmailVerifyOtp(request);
            
        } else {
            log.warn("Invalid authentication request: both phone number and email are missing.");
            throw new InvalidAuthenticationRequestException(
                    "Invalid authentication request: both phone number and email are missing.");
        }
    }
    
    private AuthenticationResponse handlePhoneNumberVerifyOtp(
            AuthenticationRequest request){
        
        try {
            log.info("Starting OTP verification for phone number: {}",
                    request.getPhoneNumber());
            
            String phoneNumber = userValidation
                    .validateAndNormalizePhoneNumber(request.getPhoneNumber());
            
            String otpRedisKey = OTP_KEY + phoneNumber;
            
            String storedOtp = redisTemplate.opsForValue().get(otpRedisKey);
            
            authValidation.validateOtp(storedOtp, request.getOtp(), phoneNumber);
            
            redisTemplate.delete(otpRedisKey);
            
            log.info("OTP verified successfully for phone number: {}",
                    phoneNumber);
            
            User user = userRepository.findByPhoneNumber(phoneNumber)
                    .orElseGet(()->{
                        
                        log.info("User not found. Creating new user.");
                        
                        User newUser = User.builder()
                                .phoneNumber(phoneNumber)
                                .build();
                        
                        return userRepository.save(newUser);
                    });
            
            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);
            
            String refreshTokenKey = REFRESH_TOKEN_KEY + user.getId();
            
            redisTemplate.opsForValue().set(refreshTokenKey,
                    refreshToken,
                    Duration.ofMillis(refreshTokenExpiration));
            
            return AuthenticationResponse.builder()
                    .message("OTP verified successfully")
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .userId(user.getId())
                    .build();
            
        }catch (OtpExpiredException | InvalidOtpException e){
        
            log.warn("OTP verification failed : {}", e.getMessage());
            throw e;
            
        }catch (RuntimeException e){
        
            log.error("Unexpected error during OTP verification for phone number: {}",
                    request.getPhoneNumber(), e);
            throw e;
        }
    }
    
    
    private AuthenticationResponse handleEmailVerifyOtp(
            AuthenticationRequest request){
        
        try{
            log.info("Starting OTP verification for email: {}",
                    request.getEmail());
            
            String email = userValidation.validateAndNormalizeEmail(
                    request.getEmail());
            
            String otpRedisKey = OTP_KEY + email;
            
            String storedOtp = redisTemplate.opsForValue().get(otpRedisKey);
            
            authValidation.validateOtp(storedOtp, request.getOtp(), email);
            
            redisTemplate.delete(otpRedisKey);
            
            log.info("OTP verified successfully for email: {}",
                    email);
            
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseGet(()->{
                        
                        log.info("User not found. Creating new user.");
                        
                        User newUser = User.builder()
                                .email(email)
                                .build();
                        
                        return userRepository.save(newUser);
                    });
            
            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);
            
            String refreshTokenKey = REFRESH_TOKEN_KEY + user.getId();
            
            redisTemplate.opsForValue().set(refreshTokenKey,
                    refreshToken,
                    Duration.ofMillis(refreshTokenExpiration));
            
            return AuthenticationResponse
                    .builder()
                    .message("OTP verified successfully")
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .userId(user.getId())
                    .build();
            
        } catch (OtpExpiredException | InvalidOtpException e) {
            
            log.warn("OTP verification failed {}",
                     e.getMessage());
            throw e;
            
        } catch (RuntimeException e) {
            
            log.error("Unexpected error during OTP verification for email: {}",
                    request.getEmail(), e);
            throw e;
        }
    }
}
