package com.example.sms.validation;

/** Interface for validating authentication-related operations in the
 * application. This interface defines methods for validating refresh tokens,
 * OTPs, and OTP request limits to ensure secure authentication processes.
 * Implementations of this interface will provide the logic for these
 * validations based on the application's requirements. */
public interface AuthValidation {
    
    /** Validates the provided refresh token against the expected user ID and key.
     *
     * @param refreshToken the refresh token to validate
     * @param userId the ID of the user associated with the refresh token
     * @param key the key used for validation (e.g., secret key or signing key)
     * @throws IllegalArgumentException if the refresh token is invalid or
     * does not match the expected user ID and key
     */
    void validateRefreshToken(
        String refreshToken,
        String userId,
        String key
    );
    
    /** Validates the provided OTP against the stored OTP for a specific phone number.
     *
     * @param storedOtp the OTP that was previously generated and stored for the phone number
     * @param providedOtp the OTP provided by the user for validation
     * @param phoneNumber the phone number associated with the OTP
     * @throws IllegalArgumentException if the provided OTP does not match the stored OTP
     * or if the phone number is invalid
     */
    void validateOtp(
        String storedOtp,
        String providedOtp,
        String phoneNumber
    );
    
    /** Validates the OTP request limit for a specific phone number within a defined time window.
     *
     * @param identifier the phone number for which to validate the OTP request limit
     * @param otpLimit the maximum number of OTP requests allowed within the time window
     * @param windowMinutes the duration of the time window in minutes
     * @param rateKeyPrefix the prefix used for rate limiting keys (e.g., "otp_request")
     * @throws IllegalStateException if the OTP request limit has been exceeded for the phone number
     */
    void validateOtpRequestLimit(
            String identifier,
            int otpLimit,
            int windowMinutes,
            String rateKeyPrefix
    );
}
