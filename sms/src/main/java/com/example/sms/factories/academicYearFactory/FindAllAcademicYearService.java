package com.example.sms.factories.academicYearFactory;

import com.example.sms.model.AcademicYear;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling the retrieval of all academic years.
 * It implements the AcademicYearOperations interface to define the specific
 * operation for finding all academic years. The service retrieves all academic
 * year entities from the repository, maps them to response objects, and returns
 * a response containing the list of academic years.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllAcademicYearService implements AcademicYearOperations{
   
    /** Mapper for converting between AcademicYear entities and
     * AcademicYearResponse objects. */
    private final AcademicYearMapper academicYearMapper;
    
    /** Repository for performing CRUD operations on AcademicYear entities. */
    private final AcademicYearRepository academicYearRepository;
    
    /** Mapper for converting between Branch entities and BranchResponse objects,
     * used to include branch details in the academic year responses. */
    private final BranchMapper branchMapper;
    
    /**
     * Returns the type of academic year request this service handles,
     * which is FIND_ALL.
     *
     * @return RequestType.FIND_ALL
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.FIND_ALL;
    }
    
    /**
     * Performs the operation to retrieve all academic years based on the provided
     * request. It retrieves all academic year entities from the repository, maps
     * them to response objects, and returns a response containing the list of
     * academic years.
     *
     * @param request The AcademicYearRequest for finding all academic years.
     * @return An AcademicYearResponse containing the list of academic years.
     */
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
        
        log.info("Starting find all process for Academic Year");
        
        List<AcademicYear> academicYearList = academicYearRepository.findAll();
        
        List<AcademicYearResponse> academicYearResponseList =
                new ArrayList<>();
        
        for(AcademicYear academicYear : academicYearList){
            log.info("Mapping Academic Year with ID: {} to response",
                    academicYear.getId());
            
            BranchResponse branchResponse = branchMapper.toResponse(
                    academicYear.getBranch(),null,null);
            
            AcademicYearResponse academicYearResponse = academicYearMapper
                    .toResponse(academicYear, branchResponse);
            
            academicYearResponseList.add(academicYearResponse);
            
        }
        
        return AcademicYearResponse.builder()
                .academicYearResponseList(academicYearResponseList)
                .build();
    }
}
