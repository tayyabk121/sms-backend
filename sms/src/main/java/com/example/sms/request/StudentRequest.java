package com.example.sms.request;

import com.example.sms.model.SchoolClass;
import com.example.sms.util.Gender;
import com.example.sms.util.RequestType;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a request to create or update a student in the system.
 * This class contains all the necessary information about the student,
 * including their personal details, class information, and status.
 * The RequestType field indicates whether the request is for creating
 * a new student or updating an existing one.
 */
@Getter
@Setter
@Builder
public class StudentRequest {
    
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
    
    private RequestType requestType;
}
