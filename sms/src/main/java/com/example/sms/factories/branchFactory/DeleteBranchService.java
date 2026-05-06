package com.example.sms.factories.branchFactory;

import com.example.sms.entity.Branch;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeleteBranchService implements BranchOperation{
    
    private final BranchValidation branchValidation;
    
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.DELETE;
    }
    
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
