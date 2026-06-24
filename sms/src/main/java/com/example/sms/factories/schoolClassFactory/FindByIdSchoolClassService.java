package com.example.sms.factories.schoolClassFactory;

import com.example.sms.model.SchoolClass;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.mapper.SchoolClassMapper;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolClassResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.SchoolClassHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the retrieval of a school class by its ID.
 * It implements the SchoolClassOperation interface to define the specific
 * operation for finding a school class by ID.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindByIdSchoolClassService implements SchoolClassOperation{

    /** Validation service for verifying the existence and validity
     *  of SchoolClass entities. */
    private final SchoolClassHelper schoolClassValidation;
    
    /** Mapper for converting between SchoolClass entities and
     * SchoolClassResponse objects. */
    private final SchoolClassMapper schoolClassMapper;
    
    /** Mapper for converting between Branch entities and BranchResponse objects,
     * used to include branch details in the school class response. */
    private final BranchMapper branchMapper;
    
    /** Response object for AcademicYear, used to include academic year
     * details in the
     * school class response. */
    private final AcademicYearMapper academicYearMapper;
    
    /**
     * Returns the type of school class request this service handles,
     * which is FIND_BY_ID.
     *
     * @return RequestType.FIND_BY_ID
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.FIND_BY_ID;
    }
    
    /**
     * Performs the operation to find a school class by its ID based on
     * the provided request.
     * It validates the existence of the school class, retrieves it from
     * the validation service, and returns a response containing
     * the school class details.
     *
     * @param request The SchoolClassRequest containing the ID of the
     * school class to be retrieved.
     * @return A SchoolClassResponse containing the details of the found
     * school class.
     */
    @Override
    public SchoolClassResponse performOperation(SchoolClassRequest request) {
        
        String schoolClassId = request.getId();
        
        log.info("Finding school class with id: {}",
                schoolClassId);
        
        SchoolClass schoolClass = schoolClassValidation.
                findById(schoolClassId);
        
        log.info("School class with id: {} found successfully",
                schoolClassId);
        
        BranchResponse branchResponse = branchMapper.toResponse(
                schoolClass.getBranch(), null, null);
        
        AcademicYearResponse academicYearResponse = academicYearMapper.toResponse(
                schoolClass.getAcademicYear(), null);
        
        return schoolClassMapper.toResponse(
                schoolClass, branchResponse, academicYearResponse);
    }
}
