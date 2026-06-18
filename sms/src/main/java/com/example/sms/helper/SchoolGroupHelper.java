package com.example.sms.helper;

import com.example.sms.model.SchoolGroup;

/**
 * Interface for validating operations related to SchoolGroup entities.
 */
public interface SchoolGroupHelper {
   
    /** Validates the SchoolGroup.
     * @param id The SchoolGroup entity to be validated.
     * @throws IllegalArgumentException if the validation fails.
     */
    SchoolGroup findById(String id);
    
    /** Validates the SchoolGroup for DELETE.
     * @param schoolGroup The SchoolGroup entity to be validated.
     * @throws IllegalArgumentException if the validation fails.
     */
     void delete(SchoolGroup schoolGroup);

}
