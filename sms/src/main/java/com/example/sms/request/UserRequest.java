package com.example.sms.request;

import com.example.sms.util.AdminStatus;
import com.example.sms.util.RequestType;
import com.example.sms.util.StaffRole;
import com.example.sms.util.UserRole;
import lombok.Getter;
import lombok.Setter;

/** Represents a request to create or update a user in the system, containing
 * all necessary information about the user, such as their name, email,
 * phone number, role, and status. This class is used to transfer user data
 * between the client and the server when performing user-related operations. */
@Getter
@Setter
public class UserRequest {
    
    private String id;
    private String schoolGroupId;
    private String branchId;
    private String name;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private UserRole role;
    private String permissions;
    private AdminStatus status;
    private RequestType requestType;
    private StaffRole staffRole;
    private String otp;
}
