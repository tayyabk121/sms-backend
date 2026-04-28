package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.SchoolGroupRequestType;

public interface SchoolGroupOperations {
    
    SchoolGroupRequestType getSchoolGroupRequestType();
    
    SchoolGroupResponse performOperation(SchoolGroupRequest request);
    
}
