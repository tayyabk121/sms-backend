package com.example.sms.request;

import com.example.sms.util.RequestType;
import com.example.sms.util.StaffRole;
import com.example.sms.util.StaffStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Request class for staff-related operations.
 */
@Getter
@Setter
public class StaffRequest {
    private String id;
    private String branchId;
    private String employeeNo;
    private String name;
    private StaffRole role;
    private String phoneNumber;
    private String email;
    private String bankAccountNumber;
    private String bankName;
    private String ifscCode;
    private String panCardNumber;
    private StaffStatus status;
    private LocalDate joinedOn;
    private RequestType requestType;
}
