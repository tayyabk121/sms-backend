package com.example.sms.factories.academicYearFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.exception.BranchIdNotFoundException;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.requestType.AcademicYearRequestType;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the creation of academic years.
 * It implements the AcademicYearOperations interface to define the specific
 * operation for creating an academic year. The service validates the
 * existence of the associated branch, maps the request to an AcademicYear
 * entity, saves it to the repository, and returns a response indicating
 * the success of the operation.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class CreateAcademicYearService implements AcademicYearOperations {

    /** Repository for performing CRUD operations on AcademicYear entities. */
    private final AcademicYearRepository academicYearRepository;
    
    /** Mapper for converting between AcademicYearRequest and AcademicYear entities. */
    private final AcademicYearMapper academicYearMapper;
    
    /** Validation service for verifying the existence and validity
     *  of Branch entities. */
    private final BranchValidation branchValidation;

    /**
     * Returns the type of academic year request this service handles,
     * which is CREATE.
     *
     * @return AcademicYearRequestType.CREATE
     */
    @Override
    public AcademicYearRequestType getRequestType() {
        return AcademicYearRequestType.CREATE;
    }

    /**
     * Performs the operation to create a new academic year based on the provided
     * request. It validates the existence of the associated branch, maps the
     * request to an AcademicYear entity, saves it to the repository, and returns
     * a response indicating the success of the operation.
     *
     * @param request The AcademicYearRequest containing the details for creating
     * a new academic year.
     * @return An AcademicYearResponse indicating the success of the operation.
     */
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
            log.info("Performing academic year operation for request: {}",
                    request);
        
        Branch branch = branchValidation.findById(request.getBranchId());
            
            AcademicYear entity = academicYearMapper.toEntity(
                    request, branch);
            
            academicYearRepository.save(entity);
            
                return AcademicYearResponse.builder()
                        .message("Academic year created successfully")
                        .build();
    }
}
