package com.example.sms.response;

import lombok.Data;

@Data
public class StudentResponse {

    private String studentId;
    private String fullName;
    private String fathersName;
    private String mothersName;
    private String gradeName;
    private String address;
    private String phoneNumber;
    private String branchId;
    private String teacherId;
}
