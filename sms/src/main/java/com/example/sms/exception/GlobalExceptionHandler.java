package com.example.sms.exception;

import jakarta.servlet.http.HttpServletRequest;
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
   
    /** Handles CreateFailedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = CreateFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponseDTO handleCreateFailedException(
            final CreateFailedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
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
    
    /** Handles InvalidAuthenticationRequestException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = InvalidAuthenticationRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponseDTO handleInvalidAuthenticationRequestException(
            final InvalidAuthenticationRequestException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles InvalidRefreshTokenException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO handleInvalidRefreshTokenException(
            final InvalidRefreshTokenException e,
            final HttpServletRequest request){
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles OtpBlockedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = OtpBlockedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    private ErrorResponseDTO handleOtpBlockedException(
            final OtpBlockedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles OtpExpiredException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = OtpExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ErrorResponseDTO handleOtpExpiredException(
            final OtpExpiredException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles InvalidOtpException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = InvalidOtpException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    private ErrorResponseDTO handleInvalidOtpException(
            final InvalidOtpException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles InvalidTokenTypeException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = InvalidTokenTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResponseDTO handleInvalidTokenTypeException(
            final InvalidTokenTypeException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles MailSendFailedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = MailSendFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ErrorResponseDTO handleMailSendFailedException(
            final MailSendFailedException e,
            final HttpServletRequest request){
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    /** Handles MailSendFailedException and returns a standardized error response.
     *
     * @param e The exception that was thrown.
     * @param request The HTTP request that caused the exception.
     * @return An ErrorResponseDTO containing details about the error.
     */
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleUserNotFoundException(
            final UserNotFoundException e,
            final HttpServletRequest request){
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
}
