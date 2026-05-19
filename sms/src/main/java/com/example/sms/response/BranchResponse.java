package com.example.sms.response;

import com.example.sms.util.BranchStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * BranchResponse is a data transfer object (DTO) used to represent the response
 * for branch-related operations in the School Management System (SMS).
 * It contains information about a branch, including its ID, associated school
 * group, name, address, contact details, academic years, status, and any
 * relevant messages.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchResponse {
    
    private String id;
    
    private SchoolGroupResponse schoolGroup;
    
    private String name;
    
    private String address;
    
    private String city;
    
    private String phoneNumber;
    
    private String email;
    
    private List<AcademicYearResponse> academicYear;
    
    private BranchStatus status;
    
    private String message;
    
    private List<BranchResponse> branchResponseList;
}
