package com.example.sms.mapperImpl;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Implementation of the SchoolGroupMapper interface for converting between
 * SchoolGroup entities and their corresponding request and response objects.
 * This class provides methods for mapping a SchoolGroupRequest to a SchoolGroup
 * entity, updating an existing SchoolGroup entity with details from a request,
 * and converting a SchoolGroup entity to a SchoolGroupResponse.
 */
@Log4j2
@Component
public class SchoolGroupMapperImpl implements SchoolGroupMapper {
    
   /** Converts a SchoolGroupRequest to a SchoolGroup entity. This method takes the
    * details from the SchoolGroupRequest and creates a new SchoolGroup entity,
    * setting its properties based on the request data. */
    @Override
    public SchoolGroup toEntity(SchoolGroupRequest request) {
        
        return SchoolGroup.builder()
                .name(request.getName())
                .logoUrl(request.getLogoUrl())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();
    }
    
    /** Converts a SchoolGroup entity to a SchoolGroupResponse, which can be returned
     * to the client. This method takes the details from the SchoolGroup entity and
     * creates a new SchoolGroupResponse object, setting its properties based on the
     * entity data. */
    @Override
    public SchoolGroupResponse toResponse(SchoolGroup schoolGroup) {
        
        return SchoolGroupResponse.builder()
                .id(schoolGroup.getId())
                .name(schoolGroup.getName())
                .logoUrl(schoolGroup.getLogoUrl())
                .email(schoolGroup.getEmail())
                .phoneNumber(schoolGroup.getPhoneNumber())
                .address(schoolGroup.getAddress())
                .build();
    }
    
    /** Updates an existing SchoolGroup entity with details from
     * a SchoolGroupRequest. This method checks each field in
     * the request and updates the corresponding property in the SchoolGroup
     * entity if the request field is not null or empty.
     * It ensures that only the provided fields in the request are
     * updated in the SchoolGroup entity, allowing for partial updates
     * while maintaining existing data that is not included in the request. */
    @Override
    public SchoolGroup toUpdate(
            SchoolGroupRequest request, SchoolGroup schoolGroup) {
        
        if(request.getName() != null && !request.getName().isEmpty()) {
            schoolGroup.setName(request.getName());
        }
        if(request.getLogoUrl() != null && !request.getLogoUrl().isEmpty()) {
            schoolGroup.setLogoUrl(request.getLogoUrl());
        }
        if(request.getEmail() != null && !request.getEmail().isEmpty()) {
            schoolGroup.setEmail(request.getEmail());
        }
        if(request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            schoolGroup.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getAddress() != null && !request.getAddress().isEmpty()) {
            schoolGroup.setAddress(request.getAddress());
        }
        
        return schoolGroup;
    }
}
