package com.example.sms.validation;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.request.SchoolGroupRequest;

import java.util.List;

public interface SchoolGroupValidation {
    
    SchoolGroup FindById(String name);
    
//    SchoolGroup create(SchoolGroup schoolGroup);
    
    SchoolGroup update(SchoolGroupRequest request,
                       SchoolGroup schoolGroup);
    
     void delete(SchoolGroup schoolGroup);
     
     List<SchoolGroup> schoolGroupList();

}
