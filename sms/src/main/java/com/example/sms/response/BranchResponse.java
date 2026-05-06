package com.example.sms.response;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.SchoolGroup;
import com.example.sms.util.BranchStatus;
import com.example.sms.util.requestType.BranchRequestType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchResponse {
    
    private String id;
    
    private SchoolGroupResponse schoolGroupId;
    
    private String name;
    
    private String address;
    
    private String city;
    
    private String phoneNumber;
    
    private String email;
    
    private List<AcademicYearResponse> academicYearId;
    
    private BranchStatus status;
    
    private String message;
    
    private List<BranchResponse> branchResponseList;
}
