package com.example.sms.response;

import com.example.sms.util.StaffRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * Response class for staff-related operations.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StaffResponse {
    private String id;
    private BranchResponse branchId;
    private String employeeNo;
    private String name;
    private StaffRole role;
    private String phoneNumber;
    private String email;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String panCardNumber;
    private String status;
    private LocalDate joinedOn;
    private String message;
    private List<StaffResponse> staffResponsesList;
}
