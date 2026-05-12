package com.example.sms.validationImpl;

import com.example.sms.entity.SchoolClass;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.exception.SchoolGroupIdNotFoundException;
import com.example.sms.repository.SchoolClassRepository;
import com.example.sms.validation.SchoolClassValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the SchoolClassValidation interface, responsible for
 * validating and managing SchoolClass entities. This class provides methods
 * to find a SchoolClass by its ID and to delete a SchoolClass, with appropriate
 * logging and exception handling.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class SchoolClassValidationImpl implements SchoolClassValidation {
   
    /** Repository for accessing SchoolClass entities from the database. */
    private final SchoolClassRepository schoolClassRepository;
    
    /** Logger for logging information and errors related to
     * SchoolClass operations. */
    @Override
    public SchoolClass findById(String id) {
        
        log.info("Attempting to find School Class with ID: {}",
                id);
        
        return schoolClassRepository.findById(id).orElseThrow(()->
                new SchoolGroupIdNotFoundException(
                        "School Class with id " + id + " not found"));
    }
    
    /** Method to delete a SchoolClass entity, with logging and
     * exception handling. */
    @Override
    public void delete(SchoolClass schoolClass) {
        
        log.info("Attempting to delete School Class with ID: {}",
                schoolClass.getId());
        
        try{
            schoolClassRepository.delete(schoolClass);
            log.info("School Class with ID: {} deleted successfully",
                    schoolClass.getId());
        }catch (Exception e){
            throw new DeleteFailedException(
                    "Failed to delete School Class : " + e.getMessage());
        }
    
    }
}
