package com.example.sms.controller;

import com.example.sms.factories.authFactory.AuthenticationFactory;
import com.example.sms.factories.authFactory.AuthenticationOperation;
import com.example.sms.request.AuthenticationRequest;
import com.example.sms.response.AuthenticationResponse;
import com.example.sms.util.AuthenticationRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for handling authentication-related API requests.
 * Acts as the entry point for all authentication operations.
 */
@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
   
    /** Factory responsible for creating authentication operations based on request type. */
    private final AuthenticationFactory authenticationFactory;

    /**
     * Handles authentication API requests and delegates processing to the appropriate
     * authentication operation based on the request type.
     *
     * @param request the authentication request payload
     * @return response containing the result of the authentication operation
     */
    @PostMapping("/authenticate")
    public AuthenticationResponse authenticateUser(
            @RequestBody final AuthenticationRequest request){
        
        AuthenticationRequestType requestType = request.getAuthRequestType();
        log.info("Authentication request type: {}", requestType);
        
        AuthenticationOperation operation = authenticationFactory
                .getOperation(requestType);
        
        return operation.performOperation(request);
    }
    
    /**
     * Health check endpoint to verify that the service is running.
     *
     * @return a response indicating the health status of the service
     */
    @GetMapping("/health-check")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Service is up and running!");
    }
    

}
