package com.example.sms.response;

import com.example.sms.entity.SchoolClass;
import com.example.sms.util.Gender;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
