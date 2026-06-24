package com.example.sms.factories.SubjectFactory;

import com.example.sms.model.Branch;
import com.example.sms.model.Subject;
import com.example.sms.mapper.SubjectMapper;
import com.example.sms.repository.SubjectRepository;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.SubjectResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.BranchHelper;
import com.example.sms.helper.SubjectHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the update operation for Subject entities
 * in the school management system. This class implements the SubjectOperations
 * interface and provides the logic to update an existing Subject based on the
 * details provided in a SubjectRequest.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateSubjectService implements SubjectOperations{
   
    /** Validation service for Subject entities, used to validate the existence
     * of a Subject by its ID before performing the update operation. */
    private final SubjectHelper subjectValidation;
    
    /** Validation service for Branch entities, used to validate the existence of
     * a Branch by its ID before associating it with the Subject during the
     * update operation. */
    private final BranchHelper branchHelper;
    
    /** Mapper for converting between Subject entities and their corresponding
     * request and response objects, used to map the details from a SubjectRequest
     * to a Subject entity during the update operation. */
    private final SubjectMapper subjectMapper;
    
    /** Repository for accessing Subject entities in the database, used to save the
     * updated Subject entity after performing the update operation. */
    private final SubjectRepository subjectRepository;
    
    /** Method to get the type of request that this service handles, which is
     * RequestType.UPDATE. This method is used to identify the type of
     * operation that this service is responsible for when processing requests. */
    @Override
    public RequestType getRequestType() {
        return RequestType.UPDATE;
    }
    
    /** Method to perform the update operation for a Subject entity based on the
     * details provided in a SubjectRequest. This method validates the existence
     * of the Subject and Branch entities, updates the Subject entity with the
     * new details from the request, saves the updated Subject entity to the
     * database, and returns a SubjectResponse indicating the success of the
     * operation. */
    @Override
    public SubjectResponse performOperation(SubjectRequest request) {
        
        log.info("Performing UPDATE operation for Subject with id: {}",
                request.getId());
        
        Subject subject = subjectValidation.findById(request.getId());
        
        Branch branch = branchHelper.findById(request.getBranchId());
        
        Subject update = subjectMapper.toUpdate(request, subject, branch);
        
        subjectRepository.save(update);
        
        return SubjectResponse.builder()
                .message("Subject with id " + request.getId()
                        + " updated successfully")
                .build();
    }
}
