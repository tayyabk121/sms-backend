package com.example.sms.exception;

/**
 * Custom exception thrown when a branch with a specified ID is not found.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class BranchIdNotFoundException extends RuntimeException{
    
    /** Constructs a new BranchIdNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public BranchIdNotFoundException(String message) {
        super(message);
    }
}
