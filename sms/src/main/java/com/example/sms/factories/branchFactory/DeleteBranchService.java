package com.example.sms.factories.branchFactory;

import com.example.sms.model.Branch;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.BranchHelper;
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
    private final BranchHelper branchHelper;
    
    /**
     * Returns the type of branch request this service handles,
     * which is DELETE.
     *
     * @return RequestType.DELETE
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.DELETE;
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
        
        Branch branch = branchHelper.findById(branchId);
        
        branchHelper.delete(branch);
        
        return BranchResponse.builder()
                .message("Branch deleted successfully")
                .build();
    }
}
