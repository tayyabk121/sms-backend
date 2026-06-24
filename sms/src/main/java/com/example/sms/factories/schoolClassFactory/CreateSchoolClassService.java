package com.example.sms.factories.schoolClassFactory;

import com.example.sms.model.AcademicYear;
import com.example.sms.model.Branch;
import com.example.sms.model.SchoolClass;
import com.example.sms.mapper.SchoolClassMapper;
import com.example.sms.repository.SchoolClassRepository;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.SchoolClassResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.AcademicYearHelper;
import com.example.sms.helper.BranchHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the creation of school classes.
 * It implements the SchoolClassOperation interface to define the specific
 * operation for creating a school class.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CreateSchoolClassService implements SchoolClassOperation{
    
    /** Mapper for converting between SchoolClassRequest and
     * SchoolClass entities. */
    private final SchoolClassMapper schoolClassMapper;
    
    /** Repository for performing CRUD operations on SchoolClass entities. */
    private final SchoolClassRepository schoolClassRepository;
    
    /** Validation service for verifying the existence and validity
     *  of Branch entities. */
    private final BranchHelper branchHelper;
    
    /** Validation service for verifying the existence and validity
     *  of AcademicYear entities. */
    private final AcademicYearHelper academicYearHelperImpl;
    
    /**
     * Returns the type of school class request this service handles,
     * which is CREATE.
     *
     * @return RequestType.CREATE
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.CREATE;
    }
    
    /**
     * Performs the operation to create a new school class based on
     * the provided request.
     * It validates the associated branch and academic year, maps the
     * request to an entity, saves it to the repository, and returns a response.
     *
     * @param request The SchoolClassRequest containing the details of the
     * school class to be created.
     * @return A SchoolClassResponse indicating the success of the operation.
     */
    @Override
    public SchoolClassResponse performOperation(SchoolClassRequest request) {
        
        log.info("Creating school class with name: {}",
                request.getName());
        
        Branch branch = branchHelper.findById(request.getBranchId());
        
        AcademicYear academicYear = academicYearHelperImpl.findById(
                request.getAcademicYearId());
        
        SchoolClass entity = schoolClassMapper.toEntity(
                request, branch, academicYear);
        
        schoolClassRepository.save(entity);
        
        log.info("School class Created Successfully ");
        
        return SchoolClassResponse.builder()
                .message("School class created successfully")
                .build();
        
    }
}
