package com.example.sms.factories.staffFactory;

import com.example.sms.request.StaffRequest;
import com.example.sms.response.StaffResponse;
import com.example.sms.util.RequestType;

/**
 * Interface defining operations for staff-related actions in the school management system.
 * Each implementation of this interface will handle a specific type of staff operation
 * such as creating, updating, deleting, or finding staff members.
 */
public interface StaffOperations {
    
    /** Returns the type of staff request that this operation implementation handles.
     *
     * @return The RequestType associated with this staff operation. */
    RequestType getRequestType();
    
    /** Performs the specific staff operation defined by the implementation
     * based on the provided StaffRequest.
     *
     * @param request The StaffRequest containing the necessary information
     * to perform the operation.
     * @return A StaffResponse indicating the result of the operation. */
    StaffResponse execute(StaffRequest request);
}
