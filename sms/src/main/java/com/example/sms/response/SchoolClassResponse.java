package com.example.sms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Response class for representing school class information in the School
 * Management System. This class includes fields for the class ID, branch ID,
 * academic year ID, name, grade level, section, and an optional message.
 * It also includes a list of SchoolClassResponse objects to represent
 * multiple classes if needed.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields from JSON response
public class SchoolClassResponse {
    private String id;
    private BranchResponse branch;
    private AcademicYearResponse academicYear;
    private String name;
    private Integer gradeLevel;
    private String section;
    private String message;
    private List<SchoolClassResponse> schoolClassResponseList;
}
