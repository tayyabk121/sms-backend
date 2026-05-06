package com.example.sms.controller;

import com.example.sms.factories.academicYearFactory.AcademicYearFactory;
import com.example.sms.factories.academicYearFactory.AcademicYearOperations;
import com.example.sms.factories.branchFactory.BranchFactory;
import com.example.sms.factories.branchFactory.BranchOperation;
import com.example.sms.factories.schoolGroupFactory.SchoolGroupFactory;
import com.example.sms.factories.schoolGroupFactory.SchoolGroupOperations;
import com.example.sms.factories.studentFactory.StudentFactory;
import com.example.sms.factories.studentFactory.StudentOperations;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.request.BranchRequest;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.request.StudentRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.response.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SchoolController {
    
    /**
     * This controller handles requests related to school groups and students.
     * It uses factories to delegate the operations based on the request type.
     */
    private final SchoolGroupFactory schoolGroupFactory;
    
    /** Factory for handling student-related operations. */
    private final StudentFactory studentFactory;
    
    private final BranchFactory branchFactory;
    
    private final AcademicYearFactory academicYearFactory;
    
    @PostMapping("/school")
    public SchoolGroupResponse handleSchoolGroup(
            @RequestBody SchoolGroupRequest request){
        
        log.info("Received school group request: {}", request);
        SchoolGroupOperations operation = schoolGroupFactory
                .getOperation(request.getSchoolGroupRequestType());
        
        return operation.performOperation(request);
    }
    
    @PostMapping("/branch")
    public BranchResponse handleBranch(
            @RequestBody BranchRequest request){
        
        log.info("Received branch request: {}", request);
        BranchOperation operation = branchFactory.getOperation(
                request.getBranchRequestType());
        
                return operation.performOperation(request);
    }
    
    @PostMapping("/academicYear")
    public AcademicYearResponse handleAcademicYear(
            @RequestBody AcademicYearRequest request){
        
        log.info("Received academic year request: {}", request);
        
        AcademicYearOperations operation = academicYearFactory
                .getOperation(request.getAcademicYearRequestType());
        
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
