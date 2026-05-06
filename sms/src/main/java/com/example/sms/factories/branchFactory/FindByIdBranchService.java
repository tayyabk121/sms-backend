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

@Log4j2
@Service
@RequiredArgsConstructor
public class FindByIdBranchService implements BranchOperation{
    
    private final BranchValidation branchValidation;
    
    private final BranchMapper branchMapper;
    
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.FIND_BY_ID;
    }
    
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
