package com.example.sms.mapper;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolClass;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.SchoolClassResponse;

/**
 * Mapper interface for converting between SchoolClass entities and their
 * corresponding request and response objects.
 * It defines methods for mapping a SchoolClassRequest to a SchoolClass entity,
 * updating an existing SchoolClass entity with details from a request, and
 * converting a SchoolClass entity to a SchoolClassResponse.
 */
public interface SchoolClassMapper {
    
    /** Converts a SchoolClassRequest to a SchoolClass entity,
     * associating it with the specified Branch and AcademicYear. */
    SchoolClass toEntity(SchoolClassRequest request,
                         Branch branch,
                         AcademicYear academicYear);
    
    /** Updates an existing SchoolClass entity with details from
     * a SchoolClassRequest, while maintaining the association with the
     * specified Branch and AcademicYear. */
    SchoolClass toUpdate(SchoolClassRequest request,
                         SchoolClass schoolClass,
                         Branch branch,
                         AcademicYear academicYear);
    
    /** Converts a SchoolClass entity to a SchoolClassResponse,
     * which can be returned to the client. */
    SchoolClassResponse toResponse(SchoolClass schoolClass);
}
