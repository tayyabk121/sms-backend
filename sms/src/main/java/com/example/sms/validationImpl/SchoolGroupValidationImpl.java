package com.example.sms.validationImpl;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.exception.SchoolGroupIdNotFoundException;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.SchoolGroupRepository;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.validation.SchoolGroupValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the SchoolGroupValidation interface for validating and
 * managing SchoolGroup entities.
 * This class provides methods to find a SchoolGroup by its ID and to delete a
 * SchoolGroup, with appropriate exception handling for cases where the entity
 * is not found or deletion fails.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class SchoolGroupValidationImpl implements SchoolGroupValidation {
   
    /** Repository for accessing SchoolGroup entities in the database. */
    private final SchoolGroupRepository schoolGroupRepository;
    
    /** Mapper for converting between SchoolGroup entities and DTOs. */
    @Override
    public SchoolGroup findById(String id) {
        
        return schoolGroupRepository.findById(id).orElseThrow(()->
                new SchoolGroupIdNotFoundException(
                        "School Group with id " + id + " not found"));
        
    }
    
    /** Method to delete a SchoolGroup entity from the database. It attempts to
     * delete the provided SchoolGroup and throws a DeleteFailedException if
     * the deletion fails, including details about the failure in the
     * exception message.
     * @param schoolGroup the SchoolGroup entity to be deleted
     * @throws DeleteFailedException if the deletion of the SchoolGroup fails,
     * with a message containing the name of the SchoolGroup and the error details
     */
    @Override
    public void delete(SchoolGroup schoolGroup) {
        try {
            schoolGroupRepository.delete(schoolGroup);
        }catch (Exception e){
            throw new DeleteFailedException(
                    "Failed to delete School Group with name "
                    + schoolGroup.getName() + ": " + e.getMessage());
        }
    }
}
