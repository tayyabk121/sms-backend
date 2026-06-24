package com.example.sms.request;

import com.example.sms.util.RequestType;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for handling school group-related operations.
 * This class contains fields that represent the necessary information
 * for creating, updating, or managing school groups.
 */
@Getter
@Setter
public class SchoolGroupRequest {
    
    private String schoolGroupId;
    
    private String name;
    
    private String logoUrl;
    
    private String email;
    
    private String phoneNumber;
    
    private String address;
    
    private RequestType requestType;
}
