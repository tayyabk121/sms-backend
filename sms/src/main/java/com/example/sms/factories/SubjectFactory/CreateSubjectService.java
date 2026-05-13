package com.example.sms.factories.SubjectFactory;

import com.example.sms.entity.Branch;
import com.example.sms.entity.Subject;
import com.example.sms.mapper.SubjectMapper;
import com.example.sms.repository.SubjectRepository;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.SubjectResponse;
import com.example.sms.util.requestType.SubjectRequestType;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the creation of Subject entities in the
 * school management system. This class implements the SubjectOperations
 * interface and provides the logic for creating a new Subject based on the
 * details provided in a SubjectRequest. It validates the associated Branch,
 * maps the request to a Subject entity, saves it to the database, and returns
 * a response indicating the success of the operation.
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class CreateSubjectService implements SubjectOperations{
   
    /** Mapper for converting between Subject entities and their
     * corresponding request and response objects. */
    private final SubjectMapper subjectMapper;
    
    /** Validation service for Branch entities, used to validate the
     * associated Branch when creating a Subject. */
    private final BranchValidation branchValidation;
    
    /** Repository for accessing Subject entities in the database, used to save
     * the newly created Subject. */
    private final SubjectRepository subjectRepository;
    
    /** Method to get the type of request this service handles, which is CREATE in
     * this case. This method is used to identify the type of operation being
     * performed when processing a SubjectRequest. */
    @Override
    public SubjectRequestType getRequestType() {
        return SubjectRequestType.CREATE;
    }
    
    /** Method to perform the create operation for a Subject based on the provided
     * SubjectRequest. This method validates the associated Branch, maps the
     * request to a Subject entity, saves it to the database, and returns a
     * SubjectResponse indicating the success of the operation. */
    @Override
    public SubjectResponse performOperation(SubjectRequest request) {
        
        log.info("Performing create operation for Subject with name: {}",
                request.getName());
        
        Branch branch = branchValidation.findById(request.getBranchId());
        
        Subject entity = subjectMapper.toEntity(request, branch);
        
        subjectRepository.save(entity);
        
        return SubjectResponse.builder()
                .message("Subject created successfully ")
                .build();
    }
}
