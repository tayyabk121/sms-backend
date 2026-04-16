package com.example.sms.service;

import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    StudentResponse getAllStudents(StudentRequest request);
}
