package com.example.sms.factories.authFactory;

import com.example.sms.request.AuthenticationRequest;
import com.example.sms.response.AuthenticationResponse;
import com.example.sms.util.AuthenticationRequestType;

/**
 * Interface representing an authentication operation in the school management system.
 * This interface defines the contract for performing authentication-related operations,
 * such as login, registration, or password reset.
 */
public interface AuthenticationOperation {
   
    /**
     * Retrieves the type of authentication request that this operation handles.
     *
     * @return the AuthenticationRequestType associated with this operation
     */
    AuthenticationRequestType getAuthenticationRequestType();
    
    /**
     * Performs the authentication operation based on the provided request.
     *
     * @param request the AuthenticationRequest containing the necessary information for the operation
     * @return an AuthenticationResponse containing the result of the operation
     */
    AuthenticationResponse performOperation(AuthenticationRequest request);
}
