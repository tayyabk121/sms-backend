package com.example.sms.validation;

import com.example.sms.entity.SchoolGroup;

public interface SchoolGroupValidation {
    
    SchoolGroup findById(String id);
    
     void delete(SchoolGroup schoolGroup);

}
