package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.RequestType;

/**
 * Interface defining operations for handling school group requests.
 * Each implementation will correspond to a specific type of
 * school group operation (e.g., CREATE, UPDATE, DELETE).
 */
public interface SchoolGroupOperations {
    
    /**
     * Returns the type of school group request that this operation
     * implementation handles.
     *
     * @return The RequestType associated with this operation.
     */
    RequestType getSchoolGroupRequestType();
    
    /**
     * Performs the specific operation defined by the implementation
     * based on the provided SchoolGroupRequest.
     *
     * @param request The SchoolGroupRequest containing the necessary
     * information to perform the operation.
     * @return A SchoolGroupResponse indicating the result of the
     * operation.
     */
    SchoolGroupResponse performOperation(SchoolGroupRequest request);
    
}
