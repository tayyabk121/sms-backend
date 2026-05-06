package com.example.sms.factories.branchFactory;

import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;

public interface BranchOperation {
    
    BranchRequestType getRequestType();
    
    BranchResponse performOperation(BranchRequest branchRequest);
}
