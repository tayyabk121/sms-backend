package com.example.sms.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Log4j2
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = StudentFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleCharacterNotFoundException(
            final StudentFailedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
    @ExceptionHandler(value = SchoolGroupFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponseDTO handleCharacterNotFoundException(
            final SchoolGroupFailedException e,
            final HttpServletRequest request) {
        
        return new ErrorResponseDTO(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI());
    }
    
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
}
