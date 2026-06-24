package com.example.sms.exception;

/**
 * Custom exception thrown when a student-related operation fails.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class CreateFailedException extends RuntimeException{
    
    /** Constructs a new StudentFailedException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public CreateFailedException(String message) {
        super(message);
    }
}
