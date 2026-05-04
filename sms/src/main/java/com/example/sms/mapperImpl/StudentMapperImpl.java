package com.example.sms.mapperImpl;

import com.example.sms.entity.Student;
import com.example.sms.mapper.StudentMapper;
import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StudentMapperImpl implements StudentMapper {
    
    
    @Override
    public Student toEntity(StudentRequest request) {
        return Student.builder()
                .schoolClass(request.getSchoolClass())
                .admissionNo(request.getAdmissionNo())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dob(request.getDob())
                .gender(request.getGender())
                .bloodGroup(request.getBloodGroup())
                .transport(request.getTransport())
                .status(request.getStatus())
                .admittedOn(request.getAdmittedOn())
                .build();
    }
    
    @Override
    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .schoolClass(student.getSchoolClass())
                .admissionNo(student.getAdmissionNo())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .dob(student.getDob())
                .gender(student.getGender())
                .bloodGroup(student.getBloodGroup())
                .transport(student.getTransport())
                .status(student.getStatus())
                .admittedOn(student.getAdmittedOn())
                .build();
    }
}
