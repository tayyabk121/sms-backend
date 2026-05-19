package com.example.sms.mapper;

import com.example.sms.entity.Branch;
import com.example.sms.entity.Subject;
import com.example.sms.request.SubjectRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SubjectResponse;

/**
 * Mapper interface for converting between Subject entities and their
 * corresponding request and response objects.
 * This interface defines methods for mapping a SubjectRequest to a Subject
 * entity, updating an existing Subject entity with details from a request,
 * and converting a Subject entity to a SubjectResponse.
 */
public interface SubjectMapper {
    
    /** Converts a SubjectRequest to a Subject entity, associating it with the
     * specified Branch. */
    Subject toEntity(SubjectRequest request,
                     Branch branch);
    
    /** Updates an existing Subject entity with details from a SubjectRequest. */
    Subject toUpdate(SubjectRequest request, Subject subject,
                     Branch branch);
    
    /** Converts a Subject entity to a SubjectResponse, which can be returned to
     * the client. */
    SubjectResponse toResponse(Subject subject,
                               BranchResponse branchResponse);
}
