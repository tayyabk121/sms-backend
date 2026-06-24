package com.example.sms.mapperImpl;

import com.example.sms.model.AcademicYear;
import com.example.sms.model.Branch;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the AcademicYearMapper interface for converting between
 * AcademicYear entities and their corresponding request and response objects.
 * This class provides methods for mapping an AcademicYearRequest to an AcademicYear
 * entity, updating an existing AcademicYear entity with details from a request,
 * and converting an AcademicYear entity to an AcademicYearResponse.
 */
@Log4j2
@Component
public class AcademicYearMapperImpl implements AcademicYearMapper {
    
    /** Converts an AcademicYearRequest to an AcademicYear entity, associating it
     * with the specified Branch. */
    @Override
    public AcademicYear toEntity(AcademicYearRequest request, Branch branch) {
        return AcademicYear.builder()
                .branch(branch)
                .label(request.getLabel())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrent(request.getIsCurrent())
                .build();
    }
    
    /** Updates an existing AcademicYear entity with details from an
     * AcademicYearRequest, while maintaining the association with the
     * specified Branch. */
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
            academicYear.setIsCurrent(request.getIsCurrent());
        
        return academicYear;
    }
    
    /** Converts an AcademicYear entity to an AcademicYearResponse, which can be
     * returned to the client. */
    @Override
    public AcademicYearResponse toResponse(AcademicYear academicYear,
                                           BranchResponse branchResponse) {
        return AcademicYearResponse.builder()
                .id(academicYear.getId())
                .branch(branchResponse)
                .label(academicYear.getLabel())
                .startDate(academicYear.getStartDate())
                .endDate(academicYear.getEndDate())
                .isCurrent(academicYear.getIsCurrent())
                .build();
    }
}
