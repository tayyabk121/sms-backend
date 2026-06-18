package com.example.sms.mapper;

import com.example.sms.model.Branch;
import com.example.sms.model.SchoolGroup;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;

import java.util.List;

/**
 * Mapper interface for converting between Branch entities and their
 * corresponding request and response objects.
 * It defines methods for mapping a BranchRequest to a Branch entity,
 * updating an existing Branch entity with details from a request, and
 * converting a Branch entity to a BranchResponse.
 */
public interface BranchMapper {
    
    /** Converts a BranchRequest to a Branch entity, associating it with the
     * specified SchoolGroup. */
    Branch toEntity(BranchRequest request, SchoolGroup schoolGroup);
    
        /** Updates an existing Branch entity with details from a BranchRequest,
        * while maintaining the association with the specified SchoolGroup. */
    Branch toUpdate(BranchRequest request, Branch branch,
                    SchoolGroup schoolGroup);
    
    /** Converts a Branch entity to a BranchResponse,
     * which can be returned to the client. */
    BranchResponse toResponse(Branch branch,
                              SchoolGroupResponse schoolGroupResponse,
                              List<AcademicYearResponse> academicYearResponses);
}