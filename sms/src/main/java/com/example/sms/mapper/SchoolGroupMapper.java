package com.example.sms.mapper;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;


public interface SchoolGroupMapper {
    
    SchoolGroup toEntity(SchoolGroupRequest request);
    
    SchoolGroupResponse toResponse(SchoolGroup schoolGroup);
    
    SchoolGroup toUpdate(SchoolGroupRequest request, SchoolGroup schoolGroup);
}
