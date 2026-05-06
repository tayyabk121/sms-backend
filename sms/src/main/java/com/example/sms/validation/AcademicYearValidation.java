package com.example.sms.validation;

import com.example.sms.entity.AcademicYear;

public interface AcademicYearValidation {
    
    AcademicYear findById(String id);
    
     void delete(AcademicYear academicYear);
}
