package com.example.sms.exception;

/** Custom exception thrown when an invalid token type is encountered during
 *  authentication or authorization processes. */
public class InvalidTokenTypeException extends RuntimeException{
    public InvalidTokenTypeException(String message) {
        super(message);
    }
}
