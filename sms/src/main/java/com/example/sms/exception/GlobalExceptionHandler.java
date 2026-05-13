package com.example.sms.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Global exception handler for the application.
 * This class uses @RestControllerAdvice to handle exceptions thrown by
 * controllers and return standardized error responses.
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {
   
    /** Handles StudentFailedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = StudentFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleStudentFailedException(
            final StudentFailedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles SchoolGroupFailedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = SchoolGroupFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleStudentFailedException(
            final SchoolGroupFailedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles BranchIdNotFoundException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = BranchIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleBranchIdNotFoundException(
            final BranchIdNotFoundException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles SchoolIdNotFoundException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = SchoolGroupIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleSchoolGroupIdNotFoundException(
            final SchoolGroupIdNotFoundException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles DeleteFailedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = DeleteFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleDeleteFailedException(
            final DeleteFailedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles AcademicYearIdNotFoundException and
     * returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = AcademicYearIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleAcademicYearIdNotFoundException(
            final AcademicYearIdNotFoundException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles SchoolClassIdNotFoundException and
     * returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = SchoolClassIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleSchoolClassIdNotFoundException(
            final SchoolClassIdNotFoundException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles IdNotFoundException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleIdNotFoundException(
            final IdNotFoundException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
}
