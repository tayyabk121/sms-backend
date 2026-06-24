package com.example.sms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/** * A class representing the structure of an error response.
 * This class is used to standardize the format of error responses
 * sent back to clients when exceptions occur in the application.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {
    /** Timestamp when the error occurred. */
    private LocalDateTime localDateTime;
    
    /** HTTP status code of the error. */
    private Integer status;
    
    /** Short description of the error. */
    private String error;
    
    /** Detailed message about the error. */
    private String message;
    
    /** The path that caused the error. */
    private String path;
}