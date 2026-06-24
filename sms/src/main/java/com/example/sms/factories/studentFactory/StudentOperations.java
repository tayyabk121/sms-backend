package com.example.sms.factories.studentFactory;

import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;
import com.example.sms.util.RequestType;

public interface StudentOperations {
    
    RequestType getStudentRequestType();
    
    StudentResponse performOperation(StudentRequest request);
}
