package com.example.sms.request;

import com.example.sms.util.requestType.SchoolClassRequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a request to create, update, or delete a school class.
 * This class contains the necessary information for performing operations
 * related to school classes, such as the class name, grade level, section,
 * and associated branch and academic year. The type of request (create,
 * update, delete) is determined by the schoolClassRequestType field.
 */
@Getter
@Setter
@Builder
public class SchoolClassRequest {
    
    private String id;
    private String branchId;
    private String academicYearId;
    private String name;
    private Integer gradeLevel;
    private String section;
    private SchoolClassRequestType requestType;
}
