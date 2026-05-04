package com.example.sms.controller;

import com.example.sms.factories.schoolGroupFactory.SchoolGroupFactory;
import com.example.sms.factories.schoolGroupFactory.SchoolGroupOperations;
import com.example.sms.factories.studentFactory.StudentFactory;
import com.example.sms.factories.studentFactory.StudentOperations;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.request.StudentRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SchoolGroupController {
    
    /**
     * This controller handles requests related to school groups and students.
     * It uses factories to delegate the operations based on the request type.
     */
    private final SchoolGroupFactory schoolGroupFactory;
    
    /** Factory for handling student-related operations. */
    private final StudentFactory studentFactory;
    
    @PostMapping("/school")
    public SchoolGroupResponse handleSchoolGroup(
            @RequestBody SchoolGroupRequest request){
        
        log.info("Received school group request: {}", request);
        SchoolGroupOperations operation = schoolGroupFactory
                .getOperation(request.getSchoolGroupRequestType());
        
        return operation.performOperation(request);
    }
    
    @PostMapping("/student")
    public StudentResponse handleStudent(
            @RequestBody StudentRequest request){
        
        log.info("Received student request: {}", request);
        StudentOperations operation = studentFactory.getOperation(
                request.getRequestType());
        
        return operation.performOperation(request);
    }
}
