package com.example.sms.exception;

/** Custom exception class to indicate that an OTP (One-Time Password) has been blocked.
 * This exception can be thrown when a user has exceeded the allowed number of OTP attempts
 * or when the OTP is deemed invalid for security reasons. It extends RuntimeException,
 * allowing it to be used without mandatory try-catch blocks. */
public class OtpBlockedException extends RuntimeException{
    public OtpBlockedException(String message) {
        super(message);
    }
}
