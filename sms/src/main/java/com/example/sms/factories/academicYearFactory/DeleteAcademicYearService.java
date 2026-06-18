package com.example.sms.factories.academicYearFactory;

import com.example.sms.model.AcademicYear;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.AcademicYearHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the deletion of academic years.
 * It implements the AcademicYearOperations interface to define the specific
 * operation for deleting an academic year. The service validates the
 * existence of the academic year, deletes it from the repository, and
 * returns a response indicating the success of the operation.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeleteAcademicYearService implements  AcademicYearOperations{
   
    /** Validation service for verifying the existence and validity of
     *  AcademicYear entities. */
    private final AcademicYearHelper academicYearHelperImpl;
    
        /**
        * Returns the type of academic year request this service handles,
        * which is DELETE.
        *
        * @return RequestType.DELETE
        */
    @Override
    public RequestType getRequestType() {
        return RequestType.DELETE;
    }
    
    /**
     * Performs the operation to delete an existing academic year based on
     * the provided request. It validates the existence of the academic year,
     * deletes it from the repository, and returns a response indicating the
     * success of the operation.
     *
     * @param request The AcademicYearRequest containing the ID of the
     * academic year to be deleted.
     * @return An AcademicYearResponse indicating the success of the operation.
     */
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
        
        String academicYearId = request.getId();
        
        log.info("Attempting to delete Academic Year with ID: {}",
                academicYearId);
        
        AcademicYear academicYear = academicYearHelperImpl.findById(
                academicYearId);
        
        log.info("Academic Year found: {}", academicYear.getId());
        
        academicYearHelperImpl.delete(academicYear);
        
        return AcademicYearResponse.builder()
                .message("Academic Year deleted successfully")
                .build();
    }
}
