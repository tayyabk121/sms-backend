package com.example.sms.validation;

import com.example.sms.model.User;
import com.example.sms.exception.InvalidAuthenticationRequestException;

/** Interface for validating user-related operations in the school
 *  management system. This interface defines methods for validating user
 *  information, such as retrieving a user by their ID and
 *  validating/normalizing phone numbers. Implementations of this interface
 *  will provide the actual logic for these validation operations. */
public interface UserValidation {
    
    /**
     * Validates the provided user ID and retrieves the corresponding User entity.
     *
     * @param userId the ID of the user to validate and retrieve
     * @return the User entity associated with the provided user ID
     * @throws IllegalArgumentException if the user ID is invalid or if no user is found
     */
    User validateAndGetUserById(String userId);
    
    /**
     * Validates the provided phone number and normalizes it to a standard format.
     * @param phoneNumber the phone number to validate and normalize
     * @return the normalized phone number in a standard format
     * @throws InvalidAuthenticationRequestException if the phone number is
     * invalid */
     String validateAndNormalizePhoneNumber(String phoneNumber);
     
     String validateAndNormalizeEmail(String email);
}
