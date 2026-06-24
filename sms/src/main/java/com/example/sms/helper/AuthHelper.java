package com.example.sms.helper;

/** Interface for authentication helper methods in the school management system. This interface
 * defines the contract for generating one-time passwords (OTPs) used for authentication
 * purposes. Implementations of this interface can provide different strategies for OTP
 * generation, such as using random number generators, time-based algorithms, or integrating
 * with third-party services. */
public interface AuthHelper {
    
    /**
     * Generates a one-time password (OTP) for authentication purposes.
     *
     * @return a String representing the generated OTP
     */
    String otpGenerate();
}
