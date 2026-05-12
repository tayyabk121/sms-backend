package com.example.sms.exception;

/**
 * Custom exception thrown when a delete operation fails.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class DeleteFailedException extends RuntimeException{
    
    /** Constructs a new DeleteFailedException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public DeleteFailedException(String message) {
        super(message);
    }
}
