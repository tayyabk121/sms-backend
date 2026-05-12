package com.example.sms.exception;

/**
 * Custom exception thrown when a school group operation fails.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class SchoolGroupFailedException extends RuntimeException{
    
    /** Constructs a new SchoolGroupFailedException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public SchoolGroupFailedException(String message) {
        super(message);
    }
}
