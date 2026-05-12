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

/**
 * Service class responsible for handling the retrieval of an academic year by its ID.
 * It implements the AcademicYearOperations interface to define the specific
 * operation for finding an academic year by ID. The service validates the
 * existence of the academic year, retrieves it from the repository, and
 * returns a response containing the details of the found academic year.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindByIdAcademicYearService implements AcademicYearOperations{
   
    /** Validation service for verifying the existence and validity of
     *  AcademicYear entities. */
    private final AcademicYearValidation academicYearValidation;
    
    /** Mapper for converting between AcademicYear entities and
     * AcademicYearResponse objects. */
    private final AcademicYearMapper academicYearMapper;
    
    /**
     * Returns the type of academic year request this service handles,
     * which is FIND_BY_ID.
     *
     * @return AcademicYearRequestType.FIND_BY_ID
     */
    @Override
    public AcademicYearRequestType getRequestType() {
        return AcademicYearRequestType.FIND_BY_ID;
    }
    
    /**
     * Performs the operation to retrieve an academic year by its ID based on
     * the provided request. It validates the existence of the academic year,
     * retrieves it from the repository, and returns a response containing the
     * details of the found academic year.
     *
     * @param request The AcademicYearRequest containing the ID of the academic
     * year to be retrieved.
     * @return An AcademicYearResponse containing the details of the found
     * academic year.
     */
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
