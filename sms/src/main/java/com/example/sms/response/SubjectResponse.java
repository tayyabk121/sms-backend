package com.example.sms.response;

import com.example.sms.entity.Branch;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Response class for Subject-related operations in the school management system.
 * It contains details about the subject, including its ID, associated branch ID,
 * name, and an optional message. Additionally, it can include a list of
 * SubjectResponse objects for cases where multiple subjects are returned.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectResponse {
    private String id;
    private String branchId;
    private String name;
    private String message;
    private List<SubjectResponse> subjectResponseList;
}
