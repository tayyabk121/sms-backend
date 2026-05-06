package com.example.sms.request;

import com.example.sms.entity.AcademicYear;
import com.example.sms.util.BranchStatus;
import com.example.sms.util.requestType.BranchRequestType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BranchRequest {
    
    private String branchId;
    
    private String schoolGroupId;
    
    private String name;
    
    private String address;
    
    private String city;
    
    private String phoneNumber;
    
    private String email;
    
    private List<AcademicYear> academicYear;
    
    private BranchStatus status;
    
    private BranchRequestType branchRequestType;
}
