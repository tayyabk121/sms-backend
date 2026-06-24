package com.example.sms.factories.authFactory;

import com.example.sms.model.User;
import com.example.sms.exception.InvalidRefreshTokenException;
import com.example.sms.exception.InvalidTokenTypeException;
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
 * RefreshTokenService is a service class responsible for handling the operation of refreshing
 * JWT access tokens using a valid refresh token. It implements the AuthenticationOperation
 * interface, which defines the contract for performing operations related to user authentication.
 * The service validates the provided refresh token, checks its validity against the stored
 * refresh token in Redis, and upon successful validation, generates new access and refresh tokens.
 * The new refresh token is stored in Redis with an updated expiration time, and an authentication
 * response containing the new tokens is returned.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class RefreshTokenService implements AuthenticationOperation{
   
    /** Redis key prefix for storing refresh tokens associated with user IDs. */
    private static final String REFRESH_TOKEN_KEY = "refresh";
    
    /** Utility for generating and validating JWT tokens, including access
     *  and refresh tokens. */
    private final JwtUtil jwtUtil;
    
    /** RedisTemplate for interacting with Redis to store and retrieve
     * refresh tokens. */
    private final RedisTemplate<String, String> redisTemplate;
    
    /** Validation component for authentication-related operations,
     * such as refresh token validation. */
    private final AuthValidation authValidation;
    
    /** Validation component for user-related operations, such as validating
     * and retrieving users by ID. */
    private final UserValidation userValidation;
    
    /** Expiration time for refresh tokens, injected from application properties. */
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;
    
    /**
     * Returns the type of authentication request this service handles, which is REFRESH_TOKEN.
     *
     * @return AuthenticationRequestType.REFRESH_TOKEN
     */
    @Override
    public AuthenticationRequestType getAuthenticationRequestType() {
        return AuthenticationRequestType.REFRESH_TOKEN;
    }
    
    /**
     * Performs the operation of refreshing the access token using the provided refresh token.
     * It validates the refresh token, retrieves the associated user, generates new access and
     * refresh tokens, and stores the new refresh token in Redis with an updated expiration time.
     *
     * @param request The authentication request containing the refresh token.
     * @return An AuthenticationResponse containing the new access and refresh tokens.
     * @throws InvalidRefreshTokenException if the provided refresh token is invalid or expired.
     * @throws InvalidTokenTypeException if the provided token is not a valid refresh token.
     */
    @Override
    public AuthenticationResponse performOperation(
            final AuthenticationRequest request) {
        
        try {
            String refreshToken = request.getRefreshToken();
            String userId = jwtUtil.extractUserId(refreshToken);
            
            User user = userValidation.validateAndGetUserById(userId);
            
            String key = buildRefreshTokenRedisKey(userId);
            
            authValidation.validateRefreshToken(refreshToken, userId, key);
            
            String newAccessToken = jwtUtil.generateAccessToken(user);
            
            String newRefreshToken = jwtUtil.generateRefreshToken(user);
            
            redisTemplate.opsForValue().set(key, newRefreshToken,
                    Duration.ofMillis(refreshTokenExpiration));
            
            log.info("Access token refreshed for user {}", userId);
            
            return AuthenticationResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .message("Access token refreshed successfully")
                    .build();
            
        }catch (InvalidRefreshTokenException | InvalidTokenTypeException e){
        
            log.warn("Failed to refresh access token: {}", e.getMessage());
            throw e;
            
        }catch (Exception e){
        
            log.error("Unexpected error during token refresh", e);
            throw e;
        }
        
    }
    
    /**
     * Builds the Redis key for storing the refresh token for a specific user.
     *
     * @param userId the ID of the user
     * @return the Redis key in the format "refresh:{userId}"
     */
    private String buildRefreshTokenRedisKey(String userId){
        return REFRESH_TOKEN_KEY + ":" + userId;
    }
}
