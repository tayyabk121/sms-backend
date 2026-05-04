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
    public AcademicYearResponse toResponse(AcademicYear entity) {
        return AcademicYearResponse.builder()
                .id(entity.getId())
                .branch(entity.getBranch())
                .label(entity.getLabel())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .isCurrent(entity.isCurrent()).build();
    }
}
