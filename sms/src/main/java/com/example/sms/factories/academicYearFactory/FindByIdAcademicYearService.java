package com.example.sms.factories.academicYearFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.requestType.AcademicYearRequestType;
import com.example.sms.validation.AcademicYearValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FindByIdAcademicYearService implements AcademicYearOperations{
    
    private final AcademicYearValidation academicYearValidation;
    
    private final AcademicYearMapper academicYearMapper;
    
    @Override
    public AcademicYearRequestType getRequestType() {
        return AcademicYearRequestType.FIND_BY_ID;
    }
    
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
        
        String academicYearId = request.getId();
        
        log.info("Starting find by ID process for Academic Year with ID: {}",
                academicYearId);
        
        AcademicYear academicYear = academicYearValidation.findById(
                academicYearId);
        
        log.info("Academic Year found: {}", academicYear);
        
        return academicYearMapper.toResponse(academicYear);
    }
}
