package com.example.sms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Response class for representing a school group in the system.
 * This class includes fields for the school's ID, name, email, logo URL,
 * phone number, address, and a message. It also contains a list of
 * SchoolGroupResponse objects to represent related school groups if needed.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolGroupResponse {
    
    private String id;
    private String name;
    private String email;
    private String logoUrl;
    private String phoneNumber;
    private String address;
    private String message;
    private List<SchoolGroupResponse> schoolResponseList;
}
