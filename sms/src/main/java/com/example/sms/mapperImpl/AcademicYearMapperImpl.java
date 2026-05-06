package com.example.sms.mapperImpl;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AcademicYearMapperImpl implements AcademicYearMapper {
    
    
    @Override
    public AcademicYear toEntity(AcademicYearRequest request, Branch branch) {
        return AcademicYear.builder()
                .branch(branch)
                .label(request.getLabel())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrent(request.isCurrent()).build();
    }
    
    @Override
    public AcademicYear toUpdate(AcademicYearRequest request,
                                 AcademicYear academicYear,
                                 Branch branch) {
        
        if(branch != null && !branch.getId().isEmpty()) {
            academicYear.setBranch(branch);
        }
        
        if (request.getLabel() != null && !request.getLabel().isEmpty()) {
            academicYear.setLabel(request.getLabel());
        }
        
        if (request.getStartDate() != null &&
                !request.getStartDate().toString().isEmpty()) {
            academicYear.setStartDate(request.getStartDate());
        }
        
        if (request.getEndDate() != null &&
                !request.getEndDate().toString().isEmpty()) {
            academicYear.setEndDate(request.getEndDate());
        }
            academicYear.setCurrent(request.isCurrent());
        
        return academicYear;
    }
    
    @Override
    public AcademicYearResponse toResponse(AcademicYear academicYear) {
        return AcademicYearResponse.builder()
                .id(academicYear.getId())
                .branch(academicYear.getBranch().getId())
                .label(academicYear.getLabel())
                .startDate(academicYear.getStartDate())
                .endDate(academicYear.getEndDate())
                .isCurrent(academicYear.isCurrent()).build();
    }
}
