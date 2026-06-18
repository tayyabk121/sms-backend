package com.example.sms.request;

import com.example.sms.model.AcademicYear;
import com.example.sms.util.BranchStatus;
import com.example.sms.util.RequestType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * BranchRequest is a data transfer object (DTO) that encapsulates the
 * information required to create or update a branch in the school management
 * system. It includes fields for branch identification, contact details,
 * academic years, status, and the type of request being made.
 */
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
    
    private RequestType requestType;
}
