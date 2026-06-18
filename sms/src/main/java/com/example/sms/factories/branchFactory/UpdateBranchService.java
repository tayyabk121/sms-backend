package com.example.sms.factories.branchFactory;

import com.example.sms.model.Branch;
import com.example.sms.model.SchoolGroup;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.repository.BranchRepository;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.BranchHelper;
import com.example.sms.helper.SchoolGroupHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the update of branches.
 * It implements the BranchOperation interface to define the specific
 * operation for updating a branch.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateBranchService implements BranchOperation{
   
    /** Mapper for converting between BranchRequest and Branch entities. */
    private final BranchMapper branchMapper;
    
    /** Validation service for verifying the existence and validity of
     *  Branch entities. */
    private final BranchHelper branchHelper;
    
    /** Repository for performing CRUD operations on Branch entities. */
    private final BranchRepository branchRepository;
    
    /** Validation service for verifying the existence and validity of
     *  SchoolGroup entities. */
    private final SchoolGroupHelper schoolGroupValidation;
    
        /**
        * Returns the type of branch request this service handles,
        * which is UPDATE.
        *
        * @return RequestType.UPDATE
        */
    @Override
    public RequestType getRequestType() {
        return RequestType.UPDATE;
    }
    
    /**
     * Performs the operation to update an existing branch based on the provided
     * request. It validates the existence of the branch and the associated school
     * group, updates the branch entity with the new details, saves it to the
     * repository, and returns a response indicating the success of the operation.
     *
     * @param branchRequest The BranchRequest containing the details for updating
     * an existing branch.
     * @return A BranchResponse indicating the success of the operation.
     */
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {
        
        String branchId = branchRequest.getBranchId();
        
        log.info("Starting update process for Branch with ID: {}",
                branchId);
        
        Branch branch = branchHelper.findById(branchId);
        
        SchoolGroup schoolGroupId = schoolGroupValidation.findById(
                branchRequest.getSchoolGroupId());
        
        branchMapper.toUpdate(branchRequest, branch, schoolGroupId);
        
        branchRepository.save(branch);
        
        return BranchResponse.builder()
                .message("Branch updated successfully")
                .build();
    }
}
