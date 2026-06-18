package com.example.sms.mapper;

import com.example.sms.model.Student;
import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;

public interface StudentMapper {
    
    Student toEntity(StudentRequest request);
    
    StudentResponse toResponse(Student student);
}
