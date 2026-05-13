package com.example.sms.exception;

/** Custom exception class for handling cases where an ID is not found in
 * the system. This exception is thrown when an operation is attempted on an
 * entity that does not exist in the database, such as trying to retrieve,
 * update, or delete an entity by its ID. */
public class IdNotFoundException extends RuntimeException{
    
    /** Constructs a new IdNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public IdNotFoundException(String message) {
        super(message);
    }
}
