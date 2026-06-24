package com.example.sms.exception;

/** Custom exception class for handling invalid authentication requests in the
 * school management system. This exception is thrown when an authentication
 * request fails due to invalid credentials or other issues related to the
 * authentication process. */
public class InvalidAuthenticationRequestException extends RuntimeException{
    public InvalidAuthenticationRequestException(String message) {
        super(message);
    }
}
