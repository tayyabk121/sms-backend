package com.example.sms.mapper;

import com.example.sms.model.SchoolGroup;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;

/**
 * Mapper interface for converting between SchoolGroup entities and their
 * corresponding request and response objects.
 * It defines methods for mapping a SchoolGroupRequest to a SchoolGroup entity,
 * updating an existing SchoolGroup entity with details from a request, and
 * converting a SchoolGroup entity to a SchoolGroupResponse.
 */
public interface SchoolGroupMapper {
    
    /** Converts a SchoolGroupRequest to a SchoolGroup entity. */
    SchoolGroup toEntity(SchoolGroupRequest request);
    
    /** Converts a SchoolGroup entity to a SchoolGroupResponse,
     * which can be returned to the client. */
    SchoolGroupResponse toResponse(SchoolGroup schoolGroup);
    
    /** Updates an existing SchoolGroup entity with details from
     * a SchoolGroupRequest. */
    SchoolGroup toUpdate(SchoolGroupRequest request, SchoolGroup schoolGroup);
}
