package com.example.sms.validation;

import com.example.sms.entity.SchoolClass;

/**
 * Interface for validating operations related to SchoolClass entities.
 */
public interface SchoolClassValidation {
   
    /**
     * Validates the SchoolClass.
     *
     * @param id The SchoolClass entity to be validated.
     * @throws IllegalArgumentException if the validation fails.
     */
    SchoolClass findById(String id);
    
    /**
     * Validates the Delete of an existing SchoolClass.
     *
     * @param schoolClass The SchoolClass entity to be validated.
     * @throws IllegalArgumentException if the validation fails.
     */
    void delete(SchoolClass schoolClass);
    
    
}
