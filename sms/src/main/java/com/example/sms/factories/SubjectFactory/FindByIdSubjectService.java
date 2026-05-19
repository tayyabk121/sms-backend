package com.example.sms.factories.SubjectFactory;

import com.example.sms.entity.Subject;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.mapper.SubjectMapper;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SubjectResponse;
import com.example.sms.util.requestType.SubjectRequestType;
import com.example.sms.validation.SubjectValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class for handling the FIND_BY_ID operation for Subject entities in the
 * school management system. This class implements the SubjectOperations
 * interface and provides the logic to retrieve a Subject by its ID, validate
 * the request, and map the Subject entity to a response object.
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class FindByIdSubjectService implements SubjectOperations{
   
    /** Validation component for Subject entities, used to validate the request and
     * retrieve the Subject entity based on the provided ID. */
    private final SubjectValidation subjectValidation;
    
    /** Mapper for converting between Subject entities and their corresponding response
     * objects. This is used to map the retrieved Subject entity to a
     * SubjectResponse that can be returned to the client. */
    private final SubjectMapper subjectMapper;
    
    /** Mapper for converting between Branch entities and their corresponding response
     * objects, used to include branch details in the subject response. */
    private final BranchMapper branchMapper;
    
    /** Returns the type of request that this service handles, which is
     * FIND_BY_ID. */
    @Override
    public SubjectRequestType getRequestType() {
        return SubjectRequestType.FIND_BY_ID;
    }
    
    /** Performs the FIND_BY_ID operation for a Subject entity. This method takes a
     * SubjectRequest containing the ID of the Subject to be retrieved, validates
     * the request, retrieves the Subject entity from the database, and maps it
     * to a SubjectResponse to be returned to the client. */
    @Override
    public SubjectResponse performOperation(SubjectRequest request) {
        
        log.info("Performing FIND_BY_ID operation for Subject with ID: {}",
                request.getId());
        
        Subject subject = subjectValidation.findById(
                request.getId());
        
        BranchResponse branchResponse = branchMapper.toResponse(
                subject.getBranch(),null,null);
        
        return subjectMapper.toResponse(subject,branchResponse);
    }
}
