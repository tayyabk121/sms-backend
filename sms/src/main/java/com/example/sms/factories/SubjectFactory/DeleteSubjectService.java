package com.example.sms.factories.SubjectFactory;

import com.example.sms.model.Subject;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.SubjectResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.SubjectHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/** Service class responsible for handling the deletion of a Subject entity
 * based on a provided SubjectRequest. This class implements the
 * SubjectOperations interface, allowing it to be used as part of a factory
 * pattern for handling different types of subject-related operations. It
 * uses SubjectValidation to perform necessary checks and operations related
 * to the Subject entity. */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeleteSubjectService implements SubjectOperations{
    
    /** Validation component for handling Subject-related operations, such as
     * finding a Subject by ID and deleting a Subject. This component is
     * injected into the service to perform necessary validation and operations
     * on the Subject entity during the delete operation. */
    private final SubjectHelper subjectValidation;
    
    /** Returns the type of subject request that this operation implementation
     * handles, which is DELETE in this case. This method is used to identify
     * the specific operation type when processing subject-related requests. */
    @Override
    public RequestType getRequestType() {
        return RequestType.DELETE;
    }
    
    /** Performs the delete operation for a Subject based on the provided
     * SubjectRequest. This method retrieves the Subject entity using the ID
     * from the request, validates its existence, and then proceeds to delete
     * it. It also logs the operation and returns a SubjectResponse indicating
     * the result of the deletion. */
    @Override
    public SubjectResponse performOperation(SubjectRequest request) {
        
        log.info("Performing DELETE operation for Subject with ID: {}",
                request.getId());
        
        Subject subject = subjectValidation.findById(request.getId());
        
        subjectValidation.delete(subject);
        
        return SubjectResponse.builder()
                .message("Subject with ID " + request.getId()
                        + " deleted successfully.")
                .build();
    }
}
