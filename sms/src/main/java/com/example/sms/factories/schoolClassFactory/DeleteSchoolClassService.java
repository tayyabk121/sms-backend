package com.example.sms.factories.schoolClassFactory;

import com.example.sms.model.SchoolClass;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.SchoolClassResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.SchoolClassHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the deletion of school classes.
 * It implements the SchoolClassOperation interface to define the specific
 * operation for deleting a school class.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeleteSchoolClassService implements SchoolClassOperation{
    
    /** Validation service for verifying the existence and validity of
     *  SchoolClass entities. */
    private final SchoolClassHelper schoolClassValidation;
    
    /**
     * Returns the type of school class request this service handles,
     * which is DELETE.
     *
     * @return RequestType.DELETE
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.DELETE;
    }
    
    /**
     * Performs the operation to delete an existing school class based on
     * the provided request.
     * It validates the existence of the school class, deletes it from
     * the repository, and returns a response.
     *
     * @param request The SchoolClassRequest containing the ID of the
     * school class to be deleted.
     * @return A SchoolClassResponse indicating the success of the operation.
     */
    @Override
    public SchoolClassResponse performOperation(SchoolClassRequest request) {
        
        String schoolClassId = request.getId();
        
        log.info("Attempting to delete school class with id: {}",
                schoolClassId);
        
        SchoolClass schoolClass = schoolClassValidation
                .findById(schoolClassId);
        
        schoolClassValidation.delete(schoolClass);
        
        log.info("School class with id: {} deleted successfully",
                schoolClassId);
        
        return SchoolClassResponse.builder()
                .message("School class deleted successfully")
                .build();
    }
}
