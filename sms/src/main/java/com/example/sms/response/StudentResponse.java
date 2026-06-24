package com.example.sms.response;

import com.example.sms.model.SchoolClass;
import com.example.sms.util.Gender;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * StudentResponse is a data transfer object (DTO) used to represent the response
 * containing student information in the School Management System (SMS).
 * It includes details such as the student's class, admission number, name,
 * date of birth, gender, blood group, transport information, status,
 * admission date, and an optional message. This class is designed to be used
 * in API responses when retrieving student information, allowing for a
 * structured and consistent format.
 */
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
    
    private SchoolClass schoolClass;
    
    private String admissionNo;
    
    private String firstName;
    
    private String lastName;
    
    private LocalDate dob;
    
    private Gender gender;
    
    private String bloodGroup;
    
    private Transport transport;
    
    private StudentStatus status;
    
    private LocalDate admittedOn;
    
    private String message;
}
