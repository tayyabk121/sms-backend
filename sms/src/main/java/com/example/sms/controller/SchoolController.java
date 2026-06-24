package com.example.sms.controller;

import com.example.sms.factories.SubjectFactory.SubjectFactory;
import com.example.sms.factories.SubjectFactory.SubjectOperations;
import com.example.sms.factories.academicYearFactory.AcademicYearFactory;
import com.example.sms.factories.academicYearFactory.AcademicYearOperations;
import com.example.sms.factories.branchFactory.BranchFactory;
import com.example.sms.factories.branchFactory.BranchOperation;
import com.example.sms.factories.schoolClassFactory.SchoolClassFactory;
import com.example.sms.factories.schoolClassFactory.SchoolClassOperation;
import com.example.sms.factories.schoolGroupFactory.SchoolGroupFactory;
import com.example.sms.factories.schoolGroupFactory.SchoolGroupOperations;
import com.example.sms.factories.staffFactory.StaffFactory;
import com.example.sms.factories.staffFactory.StaffOperations;
import com.example.sms.factories.studentFactory.StudentFactory;
import com.example.sms.factories.studentFactory.StudentOperations;
import com.example.sms.factories.userFactory.UserFactory;
import com.example.sms.factories.userFactory.UserOperations;
import com.example.sms.request.*;
import com.example.sms.response.*;
import com.example.sms.util.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling HTTP requests related to school groups,
 * branches, academic years, school classes, and students. It uses factories to
 * delegate the operations based on the request type. The controller defines
 * endpoints for each entity type and processes incoming requests by determining
 * the request type and invoking the appropriate operation from the corresponding
 * factory.
 */
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
    
    /** Factory for handling student-related operations based on request types. */
    private final StudentFactory studentFactory;
    
    /** Factory for handling branch-related operations based on request types. */
    private final BranchFactory branchFactory;
    
    /** Factory for handling academic year-related operations based on
     * request types. */
    private final AcademicYearFactory academicYearFactory;
    
    /** Factory for handling school class-related operations based on
     * request types. */
    private final SchoolClassFactory schoolClassFactory;
    
    /** Factory for handling subject-related operations based on request types. */
    private final SubjectFactory subjectFactory;
    
    /** Factory for handling user-related operations based on request types. */
    private final UserFactory userFactory;
    
    /** Factory for handling staff-related operations based on request types. */
    private final StaffFactory staffFactory;
    
    /** Endpoint for handling school group-related requests. It determines the
     * request type and delegates the operation to the appropriate service from
     * the school group factory. */
    @PostMapping("/school")
    public SchoolGroupResponse handleSchoolGroup(
            @RequestBody SchoolGroupRequest request){
        
        RequestType requestType =
                request.getRequestType();
        
        log.info("Received school group request Type : {}",
                requestType);
        
        SchoolGroupOperations operation = schoolGroupFactory
                .getOperation(requestType);
        
        return operation.performOperation(request);
    }
    
    /** Endpoint for handling branch-related requests. It determines the request
     * type and delegates the operation to the appropriate service from the
     * branch factory. */
    @PostMapping("/branch")
    public BranchResponse handleBranch(
            @RequestBody BranchRequest request){
        
        RequestType requestType =
                request.getRequestType();
        
        log.info("Received branch request Type : {}",
                requestType);
        
        BranchOperation operation = branchFactory.getOperation(
                requestType);
        
                return operation.performOperation(request);
    }
    
    /** Endpoint for handling academic year-related requests. It determines the
     * request type and delegates the operation to the appropriate service from
     * the academic year factory. */
    @PostMapping("/academicYear")
    public AcademicYearResponse handleAcademicYear(
            @RequestBody AcademicYearRequest request){
        
        RequestType requestType =
                request.getRequestType();
        
        log.info("Received academic year request Type : {}",
                requestType);
        
        AcademicYearOperations operation = academicYearFactory
                .getOperation(requestType);
        
        return operation.performOperation(request);
    }
    
    /** Endpoint for handling school class-related requests. It determines the
     * request type and delegates the operation to the appropriate service from
     * the school class factory. */
    @PostMapping("/school-class")
    public SchoolClassResponse handleSchoolClass(
            @RequestBody SchoolClassRequest request){
        
        RequestType requestType =
                request.getRequestType();
        
        log.info("Received school class request Type : {}",
                requestType);
        
        SchoolClassOperation operation = schoolClassFactory
                .getOperation(requestType);
        
        return operation.performOperation(request);
    }
    
    /** Endpoint for handling student-related requests. It determines the request
     * type and delegates the operation to the appropriate service from the
     * student factory. */
    @PostMapping("/student")
    public StudentResponse handleStudent(
            @RequestBody StudentRequest request){
        
        RequestType requestType = request
                .getRequestType();
        
        log.info("Received student request Type : {}",
                requestType);
        
        StudentOperations operation = studentFactory.getOperation(
                requestType);
        
        return operation.performOperation(request);
    }
    
    /** Endpoint for handling subject-related requests. It determines the request
     * type and delegates the operation to the appropriate service from the
     * subject factory. */
    @PostMapping("/subject")
    public SubjectResponse handleSubject(
            @RequestBody SubjectRequest request){
        
        RequestType requestType = request.getRequestType();
        
        log.info("Received subject request Type : {}",
                requestType);
        
        SubjectOperations operation = subjectFactory
                .getOperation(requestType);
        
        return operation.performOperation(request);
    }
    
    @PostMapping("/user")
    public UserResponse handleUser(
            @RequestBody UserRequest request){
        
        RequestType requestType = request.getRequestType();
        
        log.info("Received user request Type : {}",
                requestType);
        
        UserOperations operation = userFactory.getOperation(requestType);
        
        return operation.performOperation(request);
    }
    
    /** Endpoint for handling staff-related requests. It determines the request
     * type and delegates the operation to the appropriate service from the
     * staff factory. */
    @PostMapping("/staff")
    public StaffResponse handleStaff(
            @RequestBody StaffRequest request){
        
        RequestType requestType = request.getRequestType();
        
        log.info("Received staff request Type : {}",
                requestType);
        
        StaffOperations operation = staffFactory.getOperation(requestType);
        
        return operation.execute(request);
    }
    
}
