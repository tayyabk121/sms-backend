package com.example.sms.mapperImpl;

import com.example.sms.entity.Student;
import com.example.sms.mapper.StudentMapper;
import com.example.sms.request.StudentRequest;
import com.example.sms.response.StudentResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the StudentMapper interface for converting between
 * Student entities and their corresponding request and response objects.
 * This class provides methods for mapping a StudentRequest to a Student
 * entity and converting a Student entity to a StudentResponse.
 */
@Log4j2
@Component
public class StudentMapperImpl implements StudentMapper {
    
   /** Converts a StudentRequest to a Student entity.
    * This method takes the details from the StudentRequest and creates
    * a new Student entity, setting its properties based on the request data.
    * It maps fields such as schoolClass, admissionNo, firstName, lastName,
    * dob, gender, bloodGroup, transport, status, and admittedOn from
    * the request to the corresponding fields in the Student entity. */
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
    
    /** Converts a Student entity to a StudentResponse, which can be
     * returned to the client. This method takes the details from the Student
     * entity and creates a new StudentResponse object, setting its
     * properties based on the entity data. It maps fields such as
     * schoolClass, admissionNo, firstName, lastName, dob, gender,
     * bloodGroup, transport, status, and admittedOn from the Student
     * entity to the corresponding fields in the StudentResponse. */
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
