package com.example.sms.request;

import com.example.sms.entity.SchoolClass;
import com.example.sms.util.Gender;
import com.example.sms.util.requestType.StudentRequestType;
import com.example.sms.util.StudentStatus;
import com.example.sms.util.Transport;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    
    private StudentRequestType requestType;
}
