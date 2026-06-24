package com.example.sms.factories.branchFactory;

import com.example.sms.model.AcademicYear;
import com.example.sms.model.Branch;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.BranchHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling the retrieval of a branch by its ID.
 * It implements the BranchOperation interface to define the specific
 * operation for finding a branch by ID.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindByIdBranchService implements BranchOperation{
   
    /** Validation service for verifying the existence and validity of
     *  Branch entities. */
    private final BranchHelper branchHelper;
    
    /** Mapper for converting between Branch entities and BranchResponse objects. */
    private final BranchMapper branchMapper;
    
    /** Mapper for converting between SchoolGroup entities and
     * SchoolGroupResponse objects, used to include school group details in the
     * branch response. */
    private final SchoolGroupMapper schoolGroupMapper;
    
    /** Mapper for converting between AcademicYear entities and
     * AcademicYearResponse objects, used to include academic year details in the
     * branch response. */
    private final AcademicYearMapper academicYearMapper;
    
    
        /**
        * Returns the type of branch request this service handles,
        * which is FIND_BY_ID.
        *
        * @return RequestType.FIND_BY_ID
        */
    @Override
    public RequestType getRequestType() {
        return RequestType.FIND_BY_ID;
    }
    
    /**
     * Performs the operation to find a branch by its ID based on the provided
     * request. It validates the existence of the branch, retrieves it from
     * the repository, converts it to a BranchResponse object, and returns
     * the response.
     *
     * @param branchRequest The BranchRequest containing the ID of the branch
     * to be found.
     * @return A BranchResponse containing the details of the found branch.
     */
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {
        
        String branchId = branchRequest.getBranchId();
        
        log.info("Starting find by ID process for Branch with ID: {}",
                branchId);
        
        Branch branch = branchHelper.findById(
                branchId);
        
        log.info("Branch found: {}", branch.getId());
        
        SchoolGroupResponse schoolGroupResponse = schoolGroupMapper
                .toResponse(branch.getSchoolGroupId());
        
        List<AcademicYearResponse> academicYearResponseList =
                new ArrayList<>();
        
        for(AcademicYear academicYear :  branch.getAcademicYearId()){
            log.info("Mapping Academic Year with ID: {} to response",
                    academicYear.getId());
            
            AcademicYearResponse academicYearResponse = academicYearMapper
                    .toResponse(academicYear,null);
            
            academicYearResponseList.add(academicYearResponse);
        }
        
        return branchMapper.toResponse(
                branch, schoolGroupResponse, academicYearResponseList);
    }
}
