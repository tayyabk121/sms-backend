package com.example.sms.factories.branchFactory;

import com.example.sms.entity.Branch;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the deletion of branches.
 * It implements the BranchOperation interface to define the specific
 * operation for deleting a branch.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeleteBranchService implements BranchOperation{
   
    /** Validation service for verifying the existence and validity of
     *  Branch entities. */
    private final BranchValidation branchValidation;
    
    /**
     * Returns the type of branch request this service handles,
     * which is DELETE.
     *
     * @return BranchRequestType.DELETE
     */
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.DELETE;
    }
    
    /**
     * Performs the operation to delete an existing branch based on
     * the provided request.
     * It validates the existence of the branch, deletes it from
     * the repository, and returns a response.
     *
     * @param branchRequest The BranchRequest containing the ID of the
     * branch to be deleted.
     * @return A BranchResponse indicating the success of the operation.
     */
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {
        
        String branchId = branchRequest.getBranchId();
        
        log.info("Starting delete process for Branch with ID: {}",
                branchId);
        
        Branch branch = branchValidation.findById(branchId);
        
        branchValidation.delete(branch);
        
        return BranchResponse.builder()
                .message("Branch deleted successfully")
                .build();
    }
}
