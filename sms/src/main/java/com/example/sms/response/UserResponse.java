package com.example.sms.response;

import com.example.sms.util.AdminStatus;
import com.example.sms.util.StaffRole;
import com.example.sms.util.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Represents a response containing user information, including their ID, name,
 * email, phone number, role, permissions, status, and staff role. This class is
 * used to transfer user data from the server to the client when performing
 * user-related operations. */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    
    private String id;
    private SchoolGroupResponse schoolGroupId;
    private BranchResponse branchId;
    private String name;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private String permissions;
    private AdminStatus status;
    private StaffRole staffRole;
    private List<UserResponse> userResponseList;
    private String message;
}
