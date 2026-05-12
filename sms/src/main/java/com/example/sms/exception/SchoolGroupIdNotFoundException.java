package com.example.sms.exception;

/**
 * Custom exception thrown when a school group with a specified ID is not found.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class SchoolGroupIdNotFoundException extends RuntimeException{
    
    /** Constructs a new SchoolGroupIdNotFoundException with
     * the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public SchoolGroupIdNotFoundException(String message) {
        super(message);
    }
}
