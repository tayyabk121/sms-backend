package com.example.sms.factories.academicYearFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.requestType.AcademicYearRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllAcademicYearService implements AcademicYearOperations{
    
    private final AcademicYearMapper academicYearMapper;
    
    private final AcademicYearRepository academicYearRepository;
    
    @Override
    public AcademicYearRequestType getRequestType() {
        return AcademicYearRequestType.FIND_ALL;
    }
    
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
        
        log.info("Starting find all process for Academic Year");
        
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        
        List<AcademicYearResponse> list = academicYearList.stream().map(
                academicYearMapper::toResponse).toList();
        
        
        return AcademicYearResponse.builder()
                .academicYearResponseList(list)
                .build();
    }
}
