package com.example.sms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


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
