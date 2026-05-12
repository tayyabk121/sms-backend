package com.example.sms.exception;

/**
 * Custom exception thrown when an academic year with a specified ID is not found.
 * This exception extends RuntimeException, allowing it to be thrown without
 * being declared in a method's throws clause.
 */
public class AcademicYearIdNotFoundException extends RuntimeException{
    
    /** Constructs a new AcademicYearIdNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public AcademicYearIdNotFoundException(String message) {
        super(message);
    }
}
