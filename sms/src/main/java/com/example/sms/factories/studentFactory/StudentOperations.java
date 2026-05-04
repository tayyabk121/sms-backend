package com.example.sms.factories.studentFactory;

import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;
import com.example.sms.util.requestType.StudentRequestType;

public interface StudentOperations {
    
    StudentRequestType getStudentRequestType();
    
    StudentResponse performOperation(StudentRequest request);
}
