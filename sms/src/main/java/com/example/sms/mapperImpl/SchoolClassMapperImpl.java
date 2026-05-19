package com.example.sms.mapperImpl;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolClass;
import com.example.sms.mapper.SchoolClassMapper;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolClassResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the SchoolClassMapper interface for converting between
 * SchoolClass entities and their corresponding request and response objects. This
 * class provides methods for mapping a SchoolClassRequest to a SchoolClass entity,
 * updating an existing SchoolClass entity with details from a request, and
 * converting a SchoolClass entity to a SchoolClassResponse.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class SchoolClassMapperImpl implements SchoolClassMapper {
   
    /** Converts a SchoolClassRequest to a SchoolClass entity, associating it with
     * the specified Branch and AcademicYear. This method takes the details from
     * the SchoolClassRequest and creates a new SchoolClass entity, setting its
     * properties based on the request data and linking it to the provided Branch
     * and AcademicYear. */
    @Override
    public SchoolClass toEntity(SchoolClassRequest request,
                                Branch branch,
                                AcademicYear academicYear) {
        
        log.info("Mapping SchoolClassRequest to SchoolClass entity");
        
        return SchoolClass.builder()
                .branch(branch)
                .academicYear(academicYear)
                .name(request.getName())
                .gradeLevel(request.getGradeLevel())
                .section(request.getSection())
                .build();
    }
    
    /** Updates an existing SchoolClass entity with details from a SchoolClassRequest,
     * while maintaining the association with the specified Branch and AcademicYear.
     * This method checks each field in the request and updates the corresponding
     * property in the SchoolClass entity if the request field is not null or empty.
     * It also ensures that the associations with Branch and AcademicYear are
     * maintained or updated as needed. */
    @Override
    public SchoolClass toUpdate(SchoolClassRequest request,
                                SchoolClass schoolClass,
                                Branch branch,
                                AcademicYear academicYear) {
        
        log.info("Updating SchoolClass entity with");
        
        if(branch != null) {
            schoolClass.setBranch(branch);
        }
        
        if(academicYear != null) {
            schoolClass.setAcademicYear(academicYear);
        }
        
        if(request.getName() != null && !request.getName().isEmpty()) {
            schoolClass.setName(request.getName());
        }
        
        if(!(request.getGradeLevel() <= 0)) {
            schoolClass.setGradeLevel(request.getGradeLevel());
        }
        
        if (request.getSection() != null && !request.getSection().isEmpty()) {
            schoolClass.setSection(request.getSection());
        }
        
        return schoolClass;
    }
    
    /** Converts a SchoolClass entity to a SchoolClassResponse, which can be returned
     * to the client. This method takes the details from the SchoolClass entity and
     * creates a new SchoolClassResponse object, setting its properties based on the
     * entity data. It also includes the IDs of the associated Branch and AcademicYear
     * for reference in the response. */
    @Override
    public SchoolClassResponse toResponse(SchoolClass schoolClass,
                                          BranchResponse branchResponse,
                                          AcademicYearResponse academicYearResponse) {
        
        log.info("Mapping SchoolClass entity to SchoolClassResponse");
        
        return SchoolClassResponse.builder()
                .id(schoolClass.getId())
                .branch(branchResponse)
                .academicYear(academicYearResponse)
                .name(schoolClass.getName())
                .gradeLevel(schoolClass.getGradeLevel())
                .section(schoolClass.getSection())
                .build();
    }
}