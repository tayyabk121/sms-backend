package com.example.sms.validationImpl;

import com.example.sms.entity.AcademicYear;
import com.example.sms.exception.AcademicYearIdNotFoundException;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.validation.AcademicYearValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the AcademicYearValidation interface, providing
 * methods to validate and manage AcademicYear entities. This class
 * interacts with the AcademicYearRepository to perform database operations
 * and includes error handling for cases where an AcademicYear is not found
 * or when deletion fails.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class AcademicYearValidationImpl implements AcademicYearValidation {
    
    /** Repository for performing database operations related to
     * AcademicYear entities. */
    private final AcademicYearRepository academicYearRepository;
    
    /** Finds an AcademicYear by its ID. If the AcademicYear is not found,
     * an AcademicYearIdNotFoundException is thrown with a descriptive message.
     *
     * @param id the ID of the AcademicYear to find
     * @return the AcademicYear entity with the specified ID
     * @throws AcademicYearIdNotFoundException if no AcademicYear with
     * the given ID is found */
    @Override
    public AcademicYear findById(String id) {
        
        return academicYearRepository.findById(id).orElseThrow(()->
                new AcademicYearIdNotFoundException(
                        "Academic Year with id " + id + " not found"));
    }
    
    /** Deletes the specified AcademicYear entity. If the deletion fails,
     * a DeleteFailedException is thrown with a descriptive message.
     *
     * @param academicYear the AcademicYear entity to delete
     * @throws DeleteFailedException if the deletion operation fails */
    @Override
    public void delete(AcademicYear academicYear) {
        
        try{
            academicYearRepository.delete(academicYear);
        }catch (Exception e){
            throw new DeleteFailedException(
                    "Failed to delete Academic Year with label "
                    + academicYear.getLabel() + ": " + e.getMessage());
        }
    }
}
