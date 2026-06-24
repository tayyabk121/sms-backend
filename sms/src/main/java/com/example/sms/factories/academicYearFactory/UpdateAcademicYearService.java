package com.example.sms.factories.academicYearFactory;

import com.example.sms.model.AcademicYear;
import com.example.sms.model.Branch;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.AcademicYearHelper;
import com.example.sms.helper.BranchHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the update of academic years.
 * It implements the AcademicYearOperations interface to define the specific
 * operation for updating an academic year. The service validates the
 * existence of the academic year and associated branch, maps the request
 * to an updated AcademicYear entity, saves it to the repository, and returns
 * a response indicating the success of the operation.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateAcademicYearService implements AcademicYearOperations{
   
    /** Validation service for verifying the existence and validity of
     *  AcademicYear entities. */
    private final AcademicYearHelper academicYearHelperImpl;
    
    /** Mapper for converting between AcademicYearRequest and AcademicYear entities. */
    private final AcademicYearMapper academicYearMapper;
    
    /** Validation service for verifying the existence and validity of
     *  Branch entities. */
    private final BranchHelper branchHelper;
    
    /** Repository for performing CRUD operations on AcademicYear entities. */
    private final AcademicYearRepository academicYearRepository;
    
    /**
     * Returns the type of academic year request this service handles,
     * which is UPDATE.
     *
     * @return RequestType.UPDATE
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.UPDATE;
    }
    
    /**
     * Performs the operation to update an existing academic year based on the provided
     * request. It validates the existence of the academic year and the associated
     * branch, maps the request to an updated AcademicYear entity, saves it to the
     * repository, and returns a response indicating the success of the operation.
     *
     * @param request The AcademicYearRequest containing the details for updating
     * an existing academic year.
     * @return An AcademicYearResponse indicating the success of the operation.
     */
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
        
        String academicYearId = request.getId();
        
        log.info("Starting update process for Academic Year with ID: {}",
                academicYearId);
        
        AcademicYear academicYear = academicYearHelperImpl.findById(
                academicYearId);
        
        log.info("Academic Year found: {}", academicYear.getId());
        
        Branch branch = branchHelper.findById(request.getBranchId());
        
        AcademicYear update = academicYearMapper.toUpdate(
                request, academicYear, branch);
        
        academicYearRepository.save(update);
        
        return AcademicYearResponse.builder()
                .message("Academic Year updated successfully")
                .build();
    }
}
