package com.example.sms.mapperImpl;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SchoolGroupMapperImpl implements SchoolGroupMapper {
    
    
    @Override
    public SchoolGroup toEntity(SchoolGroupRequest request) {
        
        return SchoolGroup.builder()
                .name(request.getName())
                .logoUrl(request.getLogoUrl())
                .contactEmail(request.getEmail())
                .contactPhone(request.getPhoneNumber())
                .address(request.getAddress())
                .build();
    }
    
    @Override
    public SchoolGroupResponse toResponse(SchoolGroup schoolGroup) {
        
        return SchoolGroupResponse.builder()
                .id(schoolGroup.getId())
                .name(schoolGroup.getName())
                .logoUrl(schoolGroup.getLogoUrl())
                .email(schoolGroup.getContactEmail())
                .phoneNumber(schoolGroup.getContactPhone())
                .address(schoolGroup.getAddress())
                .build();
    }
    
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
            schoolGroup.setContactEmail(request.getEmail());
        }
        if(request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            schoolGroup.setContactPhone(request.getPhoneNumber());
        }
        if(request.getAddress() != null && !request.getAddress().isEmpty()) {
            schoolGroup.setAddress(request.getAddress());
        }
        
        return schoolGroup;
    }
}
