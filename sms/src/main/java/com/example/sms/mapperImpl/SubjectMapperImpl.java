package com.example.sms.mapperImpl;

import com.example.sms.entity.Branch;
import com.example.sms.entity.Subject;
import com.example.sms.mapper.SubjectMapper;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SubjectResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the SubjectMapper interface for converting between
 * Subject entities and their corresponding request and response objects.
 * This class provides methods for mapping a SubjectRequest to a Subject
 * entity, updating an existing Subject entity with details from a request,
 * and converting a Subject entity to a SubjectResponse.
 */
@Log4j2
@Component
public class SubjectMapperImpl implements SubjectMapper {
    
    /** Converts a SubjectRequest to a Subject entity.
     * This method takes the details from the SubjectRequest and creates
     * a new Subject entity, setting its properties based on the request data.
     * It maps fields such as name and code from the request to the
     * corresponding fields in the Subject entity. */
    @Override
    public Subject toEntity(SubjectRequest request ,
                            Branch branch) {
        return Subject.builder()
                .name(request.getName())
                .branch(branch)
                .build();
    }
    
    
    /** Updates an existing Subject entity with details from a SubjectRequest.
     * This method takes the details from the SubjectRequest and updates the
     * properties of an existing Subject entity based on the request data.
     * It maps fields such as name and code from the request to the
     * corresponding fields in the Subject entity. */
    @Override
    public Subject toUpdate(SubjectRequest request, Subject subject,
                            Branch branch) {
        
        if(request.getName() != null && !request.getName().isEmpty()) {
            subject.setName(request.getName());
        }
        
        if(request.getBranchId() != null && !request.getBranchId().isEmpty()) {
            subject.setBranch(branch);
        }
        return subject;
    }
    
    
    /** Converts a Subject entity to a SubjectResponse, which can be returned to
     * the client. This method takes the details from the Subject entity and
     * creates a new SubjectResponse object, setting its properties based on
     * the entity data. It maps fields such as name and code from the Subject
     * entity to the corresponding fields in the SubjectResponse. */
    @Override
    public SubjectResponse toResponse(Subject subject,
                                      BranchResponse branchResponse) {
        return SubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .branch(branchResponse)
                .build();
    }
}
