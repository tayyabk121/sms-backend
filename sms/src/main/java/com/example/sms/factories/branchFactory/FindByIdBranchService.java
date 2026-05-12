package com.example.sms.factories.branchFactory;

import com.example.sms.entity.Branch;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
    private final BranchValidation branchValidation;
    
    /** Mapper for converting between Branch entities and BranchResponse objects. */
    private final BranchMapper branchMapper;
    
        /**
        * Returns the type of branch request this service handles,
        * which is FIND_BY_ID.
        *
        * @return BranchRequestType.FIND_BY_ID
        */
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.FIND_BY_ID;
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
        
        Branch branch = branchValidation.findById(
                branchId);
        
        log.info("Branch found: {}", branch.getId());
        
        return branchMapper.toResponse(branch);
    }
}
