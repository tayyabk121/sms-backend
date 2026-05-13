package com.example.sms.request;

import com.example.sms.util.requestType.SubjectRequestType;
import lombok.Getter;
import lombok.Setter;

/**
 * Request class for creating or updating a Subject in the school management system.
 * It contains the necessary fields to represent a Subject, including its ID,
 * associated branch ID, and name.
 */
@Getter
@Setter
public class SubjectRequest {
    private String id;
    private String branchId;
    private String name;
    private SubjectRequestType subjectRequestType;
}
