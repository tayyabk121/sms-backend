package com.example.sms.validationImpl;

import com.example.sms.exception.*;
import com.example.sms.security.JwtUtil;
import com.example.sms.validation.AuthValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Implementation of authentication-related validations, including
 * refresh token validation, OTP validation, and OTP request limit checks.
 */
@Component
@Log4j2
@RequiredArgsConstructor
public class AuthValidationsImpl implements AuthValidation {
    
    /**
     * RedisTemplate for interacting with Redis to store and retrieve
     * OTPs, refresh tokens, and rate-limiting data.
     */
    private final RedisTemplate<String, String> redisTemplate;
    
    /**
     * JwtUtil for validating JWT tokens and extracting information
     * such as token type and user ID.
     */
    private final JwtUtil jwtUtil;
    
    /** Redis key prefix for tracking failed OTP attempts per phone number. */
    private static final String failKey = "otp:fail:";
    
    /** Redis key prefix for blocking phone numbers after multiple failed OTP attempts. */
    private static final String blockKey = "otp:block:";
    
    /** Validates the provided refresh token against the stored token in Redis and checks its validity.
     *
     * <p>This method performs several checks to ensure the refresh token is valid:
     * <ul>
     *     <li>Checks if the refresh token is present and not blank.</li>
     *     <li>Retrieves the stored refresh token from Redis using the provided key.</li>
     *     <li>Validates that the token type is "REFRESH".</li>
     *     <li>Compares the provided refresh token with the stored token.</li>
     *     <li>Checks if the refresh token is valid and not expired using JwtUtil.</li>
     * </ul>
     *
     * @param refreshToken the refresh token to validate
     * @param userId       the ID of the user associated with the refresh token
     * @param key          the Redis key used to retrieve the stored refresh token
     *
     * @throws InvalidRefreshTokenException if any validation check fails, indicating that
     *                                     the refresh token is invalid or expired.
     */
    @Override
    public void validateRefreshToken(String refreshToken,
                                     String userId,
                                     String key) {
        
        String tokenType = jwtUtil.extractTokenType(refreshToken);
        
        if(refreshToken == null || refreshToken.isBlank()){
            throw new InvalidRefreshTokenException(
                    "Refresh token is missing from the request");
        }
        
        String storedRefreshToken = redisTemplate.opsForValue().get(key);
        
        if(storedRefreshToken == null){
            throw new InvalidRefreshTokenException(
                    "No refresh token found for the user. Please log in again.");
        }
        
        if(!"REFRESH".equals(tokenType)){
            throw new InvalidRefreshTokenException(
                    "Invalid token type. Expected a refresh token.");
        }
        
        if(!refreshToken.equals(storedRefreshToken)){
            throw new InvalidRefreshTokenException(
                    "Invalid refresh token. Please log in again.");
        }
        
        if(!jwtUtil.isTokenValid(refreshToken, userId)){
            throw new InvalidRefreshTokenException(
                    "Refresh token is expired or invalid. Please log in again.");
        }
        
    }
    
    /** Validates the provided OTP against the stored OTP in Redis and checks for blocking conditions.
     *
     * <p>This method performs several checks to ensure the OTP is valid:
     * <ul>
     *     <li>Checks if the phone number is currently blocked due to multiple failed OTP attempts.</li>
     *     <li>Retrieves the stored OTP from Redis using the provided phone number.</li>
     *     <li>Validates that the provided OTP is not null or blank.</li>
     *     <li>Compares the provided OTP with the stored OTP.</li>
     *     <li>If the OTP is invalid, increments the failure count and blocks the phone number if necessary.</li>
     * </ul>
     *
     * @param storedOtp    the OTP stored in Redis for validation
     * @param providedOtp  the OTP provided by the user for validation
     * @param phoneNumber  the phone number associated with the OTP
     *
     * @throws OtpBlockedException       if the phone number is currently blocked due to multiple failed attempts
     * @throws OtpExpiredException       if the stored OTP has expired or is not found in Redis
     * @throws InvalidOtpException       if the provided OTP is invalid or not provided
     */
    @Override
    public void validateOtp(String storedOtp,
                            String providedOtp,
                            String phoneNumber) {
        if(redisTemplate.hasKey(blockKey + phoneNumber)){
            log.warn("Phone number {} is currently blocked due"
                            +" to multiple failed OTP attempts",
                    phoneNumber);
            throw new OtpBlockedException(
                    "Too many failed OTP attempts. Please try again later.");
        }
        
        if (storedOtp == null) {
            log.warn("OTP expired or not found in Redis");
            throw new OtpExpiredException(
                    "OTP has expired or is not available");
        }
        
        if (providedOtp == null || providedOtp.isBlank()) {
            log.warn("OTP not provided in request");
            throw new InvalidOtpException("OTP must be provided");
        }
        
        
        if (!storedOtp.equals(providedOtp)) {
            
            Long failCount = redisTemplate.opsForValue().
                    increment(failKey + phoneNumber);
            
            redisTemplate.expire(failKey + phoneNumber,
                    Duration.ofMinutes(30));
            log.warn("Invalid OTP attempt");
            if (failCount!= null && failCount >= 5) {
                redisTemplate.opsForValue().set(blockKey + phoneNumber,
                        "BLOCKED",
                        Duration.ofMinutes(30));
                log.warn("Phone number {} blocked due to multiple failed OTP attempts",
                        phoneNumber);
            }
            throw new InvalidOtpException("Invalid OTP provided");
        }
        redisTemplate.delete(failKey);
        
        log.info("OTP validated successfully");
    }
    
   /** Validates the OTP request limit for a specific identifier (phone
    * number or email) within a defined time window.
     *
     * <p>This method performs several checks to ensure that the OTP request
    * limit has not been exceeded:
     * <ul>
     *     <li>Constructs a Redis key using the provided rateKeyPrefix andidentifier.</li>
     *     <li>Retrieves the current count of OTP requests from Redis for the identifier.</li>
     *     <li>Checks if the count exceeds the specified OTP limit and throws an exception if it does.</li>
     *     <li>If the count is zero, initializes it in Redis with an expiration time based on the windowMinutes parameter.</li>
     *     <li>If the count is greater than zero, increments it in Redis.</li>
     * </ul>
     *
     * @param identifier      the identifier (phone number or email) for which to validate the OTP request limit
     * @param otpLimit        the maximum number of OTP requests allowed within the time window
     * @param windowMinutes    the duration of the time window in minutes
     * @param rateKeyPrefix   the prefix used for rate limiting keys in Redis (e.g., "otp_request:")
     *
     * @throws InvalidAuthenticationRequestException if the OTP request limit has been exceeded for the identifier
     */
    @Override
    // Generic method jo identifier (phone ya email) kuch bhi le sakta hai
    public void validateOtpRequestLimit(
            String identifier,
            int otpLimit,
            int windowMinutes,
            String rateKeyPrefix
    ) {
        log.info("Checking OTP rate limit for identifier: {}", identifier);
        String redisKey = rateKeyPrefix + identifier;
        
        String countStr = redisTemplate.opsForValue().get(redisKey);
        int count = countStr == null ? 0 : Integer.parseInt(countStr);
        
        if (count >= otpLimit) {
            log.error("OTP rate limit exceeded for identifier: {}",
                    identifier);
            throw new InvalidAuthenticationRequestException(
                    "Maximum OTP requests reached. Please try again later.");
        }
        
        if (count == 0) {
            redisTemplate.opsForValue().set(redisKey, "1",
                    Duration.ofMinutes(windowMinutes));
        } else {
            redisTemplate.opsForValue().increment(redisKey);
        }
    }
}
