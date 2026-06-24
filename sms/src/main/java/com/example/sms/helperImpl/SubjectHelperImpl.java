package com.example.sms.helperImpl;

import com.example.sms.model.Subject;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.exception.IdNotFoundException;
import com.example.sms.repository.SubjectRepository;
import com.example.sms.helper.SubjectHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/** Implementation of the SubjectValidation interface for validating and managing
 * Subject entities. This class provides methods to find a Subject by its ID
 * and to delete a Subject, with appropriate exception handling for cases where
 * the entity is not found or deletion fails. */
@Log4j2
@Component
@RequiredArgsConstructor
public class SubjectHelperImpl implements SubjectHelper {
    
    /** Repository for accessing Subject entities in the database. */
    private final SubjectRepository subjectRepository;
    
    /** Method to find a Subject by its ID. This method can be implemented to
     * retrieve a Subject from the database using its ID and return it. If the
     * Subject is not found, it can throw an appropriate exception to indicate
     * that the Subject with the specified ID does not exist. */
    @Override
    public Subject findById(String id) {
        
        return subjectRepository.findById(id).orElseThrow(()->
                new IdNotFoundException (
                        "Subject with id " + id + " not found"));
    }
    
    /** Method to delete a Subject entity from the database. It attempts to
     * delete the provided Subject and can throw an appropriate exception if
     * the deletion fails, including details about the failure in the exception
     * message. The implementation can include logic to check for any constraints
     * or associations that may prevent the deletion of the Subject and handle
     * those cases accordingly. */
    @Override
    public void delete(Subject subject) {
        
        try {
            subjectRepository.delete(subject);
        } catch (Exception e){
            throw new DeleteFailedException(
                    "Failed to delete Subject with name " + subject.getName()
                            + ": " + e.getMessage());
        }
    }
}
