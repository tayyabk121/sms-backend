package com.example.sms.factories.branchFactory;

import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.RequestType;

/**
 * Interface defining the contract for branch operations in the BranchFactory.
 * Each implementation of this interface will handle a specific type of branch
 * request, as indicated by the BranchRequestType returned by the getRequestType
 * method. The performOperation method will execute the logic for processing
 * the given BranchRequest and return a BranchResponse.
 */
public interface BranchOperation {
   
    /** Returns the type of branch request that this operation handles. */
    RequestType getRequestType();
    
    /** Performs the operation based on the provided BranchRequest and returns
     * a BranchResponse containing the result of the operation. */
    BranchResponse performOperation(BranchRequest branchRequest);
}
