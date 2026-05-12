package com.example.sms.exception;

/**
 * Custom exception thrown when a school class with a specified ID is not found.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class SchoolClassIdNotFoundException extends RuntimeException{
    
    /** Constructs a new SchoolClassIdNotFoundException with the
     * specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public SchoolClassIdNotFoundException(String message) {
        super(message);
    }
}
