package com.example.sms.validationImpl;

import com.example.sms.model.User;
import com.example.sms.exception.IdNotFoundException;
import com.example.sms.exception.InvalidAuthenticationRequestException;
import com.example.sms.repository.UserRepository;
import com.example.sms.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of UserValidation interface that provides methods to validate
 * user-related data. This class interacts with the UserRepository to fetch
 * user information and validate phone numbers.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserValidationImpl implements UserValidation {
   
    /** Repository for accessing user data from the database. */
    private final UserRepository userRepository;
    
    /** Country code to be used for normalizing phone numbers. */
    private static final String COUNTRY_CODE = "+91";
    
    /**
     * Validates the provided user ID and retrieves the corresponding User entity.
     *
     * @param userId the ID of the user to be validated and retrieved.
     * @return the User entity corresponding to the provided user ID.
     * @throws IdNotFoundException if no user is found with the provided ID.
     */
    @Override
    public User validateAndGetUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->
                new IdNotFoundException("User with id " + userId
                        + " not found"));
    }
    
    /**
     * Validates the provided phone number and normalizes it to a standard format.
     *
     * @param phoneNumber the phone number to be validated and normalized.
     * @return the normalized phone number in the format "+91XXXXXXXXXX".
     * @throws InvalidAuthenticationRequestException if the phone number
     * is null, blank, contains invalid characters, or has an incorrect
     * length after normalization.
     */
    @Override
    public String validateAndNormalizePhoneNumber(String phoneNumber) {
        
        if (phoneNumber == null || phoneNumber.isBlank()){
            log.warn("Phone number is null or blank");
            throw new InvalidAuthenticationRequestException("Phone number "
                    +"cannot be null or blank");
        }
        
        phoneNumber = phoneNumber.trim();
        
        if(!phoneNumber.matches("^\\+?\\d+$")){
            log.warn("Invalid phone number format: {}", phoneNumber);
            throw new InvalidAuthenticationRequestException(
                    "Phone number contains invalid characters");
        }
        
        if(!phoneNumber.startsWith(COUNTRY_CODE)){
        
        log.info("Normalizing phone number by adding country code: {}",
                phoneNumber);
        
        phoneNumber = COUNTRY_CODE + phoneNumber;
        
        }
        
        String normalizedPhoneNumber = phoneNumber.replace(COUNTRY_CODE,
                "");
        
        if (normalizedPhoneNumber.length() != 10) {
            log.warn("Invalid phone number length after normalization: {}",
                    normalizedPhoneNumber);
            throw new InvalidAuthenticationRequestException(
                    "Phone number must be 10 digits long after normalization");
        }
        
        return COUNTRY_CODE + normalizedPhoneNumber;
    }
    
    @Override
    public String validateAndNormalizeEmail(String email) {
        
        if (email == null || email.isBlank()) {
            log.warn("Email is null or blank");
            throw new InvalidAuthenticationRequestException("Email cannot be null or blank");
        }
        
        email = email.trim().toLowerCase();
        
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        
        if (!email.matches(emailRegex)) {
            log.warn("Invalid email format: {}", email);
            throw new InvalidAuthenticationRequestException("Email format is invalid");
        }
        
        if (email.length() > 254) {
            log.warn("Email length exceeds maximum limit: {}", email.length());
            throw new InvalidAuthenticationRequestException("Email is too long");
        }
        
        return email;
    }
}
