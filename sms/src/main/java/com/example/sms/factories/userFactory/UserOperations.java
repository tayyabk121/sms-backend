package com.example.sms.factories.userFactory;

import com.example.sms.request.UserRequest;
import com.example.sms.response.UserResponse;
import com.example.sms.util.RequestType;

/**
 * UserOperations interface defines the contract for user-related operations.
 * Implementing classes will provide specific implementations for different
 * types of user operations, such as creating, updating, deleting, or finding
 * users. Each operation is associated with a specific RequestType.
 */
public interface UserOperations {
   
    /**
     * Returns the RequestType associated with the specific user operation.
     * This allows for identifying the type of operation being performed.
     *
     * @return the RequestType for the operation
     */
    RequestType getRequestType();
    
    /**
     * Performs the user operation based on the provided UserRequest.
     * Implementing classes will define the specific logic for handling the
     * request and returning an appropriate UserResponse.
     *
     * @param userRequest the UserRequest containing the details for the operation
     * @return a UserResponse containing the result of the operation
     */
    UserResponse performOperation(UserRequest userRequest);
}
