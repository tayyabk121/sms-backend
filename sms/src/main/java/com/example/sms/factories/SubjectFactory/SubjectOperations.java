package com.example.sms.factories.SubjectFactory;

import com.example.sms.request.SubjectRequest;
import com.example.sms.response.SubjectResponse;
import com.example.sms.util.RequestType;

/** Interface defining operations for handling subject-related requests.
 * Each implementation will correspond to a specific type of
 * subject operation (e.g., CREATE, FIND_BY_ID, FIND_ALL, UPDATE, DELETE). */
public interface SubjectOperations {
    
    /** Returns the type of subject request that this operation
     * implementation handles.
     *
     * @return The RequestType associated with this operation. */
    RequestType getRequestType();
    
    /** Performs the specific operation defined by the implementation
     * based on the provided SubjectRequest.
     *
     * @param request The SubjectRequest containing the necessary
     * information to perform the operation.
     * @return A SubjectResponse indicating the result of the
     * operation. */
    SubjectResponse performOperation(SubjectRequest request);
}
