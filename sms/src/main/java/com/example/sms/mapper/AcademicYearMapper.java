package com.example.sms.mapper;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;

public interface AcademicYearMapper {
    
    AcademicYear toEntity(AcademicYearRequest request, Branch branch);
    
    AcademicYear toUpdate(AcademicYearRequest request,
                         AcademicYear academicYear,
                          Branch branch);
    
    AcademicYearResponse toResponse(AcademicYear entity);
}
