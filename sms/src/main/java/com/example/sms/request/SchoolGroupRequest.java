package com.example.sms.request;

import com.example.sms.util.requestType.SchoolGroupRequestType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SchoolGroupRequest {
    
    private String schoolGroupId;
    
    private String name;
    
    private String logoUrl;
    
    private String email;
    
    private String phoneNumber;
    
    private String address;
    
    private SchoolGroupRequestType schoolGroupRequestType;
}
