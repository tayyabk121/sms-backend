package com.example.sms.mapper;

import com.example.sms.model.AcademicYear;
import com.example.sms.model.Branch;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;

/**
 * Mapper interface for converting between AcademicYear entities and their
 * corresponding request and response objects.
 * It defines methods for mapping an AcademicYearRequest to an AcademicYear
 * entity, updating an existing AcademicYear entity with details from a request,
 * and converting an AcademicYear entity to an AcademicYearResponse.
 */
public interface AcademicYearMapper {
    
    /** Converts an AcademicYearRequest to an AcademicYear entity, associating it
     * with the specified Branch. */
    AcademicYear toEntity(AcademicYearRequest request, Branch branch);
    
    /** Updates an existing AcademicYear entity with details from an
     * AcademicYearRequest, while maintaining the association with the
     * specified Branch. */
    AcademicYear toUpdate(AcademicYearRequest request,
                         AcademicYear academicYear,
                          Branch branch);
    
    /** Converts an AcademicYear entity to an AcademicYearResponse, which can be
     * returned to the client. */
    AcademicYearResponse toResponse(AcademicYear entity,
                                    BranchResponse branchResponse);
}
