package com.example.sms.factories.schoolClassFactory;

import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.SchoolClassResponse;
import com.example.sms.util.RequestType;

/**
 * Interface for school class operations.
 */
public interface SchoolClassOperation {
    
    /** Returns the type of school class request this operation handles. */
    RequestType getRequestType();
    
    /** Performs the operation based on the provided school class
     * request and returns a response. */
    SchoolClassResponse performOperation(SchoolClassRequest request);
}
