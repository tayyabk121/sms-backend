package com.example.sms.factories.userFactory;

import com.example.sms.exception.InvalidAuthenticationRequestException;
import com.example.sms.exception.InvalidOtpException;
import com.example.sms.exception.OtpExpiredException;
import com.example.sms.model.User;
import com.example.sms.repository.UserRepository;
import com.example.sms.request.UserRequest;
import com.example.sms.response.UserResponse;
import com.example.sms.util.RequestType;
import com.example.sms.validation.AuthValidation;
import com.example.sms.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for verifying OTP (One-Time Password) for user authentication.
 * This service handles OTP verification for both phone numbers and email addresses.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class VerifyOtpUserService implements UserOperations{
    
    /**
     * Prefix for OTP keys stored in Redis.
     */
    private static final String OTP_KEY = "otp:";
    
    /**
     * Repository for accessing user data.
     */
    private final UserRepository userRepository;
    
    /**
     * Redis template for interacting with Redis data store.
     */
    private final RedisTemplate<String, String> redisTemplate;
    
    /**
     * Validation service for authentication-related operations.
     */
    private final AuthValidation authValidation;
    
    /**
     * Validation service for user-related operations.
     */
    private final UserValidation userValidation;
    
    /**
     * Returns the request type that this service handles.
     *
     * @return RequestType.VERIFY_OTP
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.VERIFY_OTP;
    }
    
    /**
     * Performs the OTP verification operation based on the provided user request.
     * It determines whether to verify OTP for a phone number or an email address
     * and delegates the verification process accordingly.
     *
     * @param request the user request containing OTP and either phone number or email
     * @return UserResponse indicating the result of the OTP verification
     * @throws InvalidAuthenticationRequestException if both phone number and email are missing in the request
     */
    @Override
    public UserResponse performOperation(UserRequest request) {
        
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
    
    /**
     * Handles OTP verification for a phone number.
     *
     * @param request the user request containing OTP and phone number
     * @return UserResponse indicating the result of the OTP verification
     * @throws OtpExpiredException if the OTP has expired
     * @throws InvalidOtpException if the provided OTP is invalid
     */
    private UserResponse handlePhoneNumberVerifyOtp(
            UserRequest request){
        
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
            
            User user = userValidation.validateAndGetUserById(
                    request.getId());
            
            user.setPhoneNumber(phoneNumber);
            
            userRepository.save(user);
            
            return UserResponse.builder()
                    .message("OTP verified successfully")
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
    
    /**
     * Handles OTP verification for an email address.
     *
     * @param request the user request containing OTP and email
     * @return UserResponse indicating the result of the OTP verification
     * @throws OtpExpiredException if the OTP has expired
     * @throws InvalidOtpException if the provided OTP is invalid
     */
    private UserResponse handleEmailVerifyOtp(
            UserRequest request){
        
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
            
            User user = userValidation.validateAndGetUserById(
                    request.getId());
            
            user.setEmail(email);
            
            userRepository.save(user);
            
            return UserResponse.builder()
                    .message("OTP verified successfully")
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
