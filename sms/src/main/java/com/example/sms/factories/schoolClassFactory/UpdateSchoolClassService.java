package com.example.sms.factories.schoolClassFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolClass;
import com.example.sms.exception.AcademicYearIdNotFoundException;
import com.example.sms.mapper.SchoolClassMapper;
import com.example.sms.repository.SchoolClassRepository;
import com.example.sms.request.SchoolClassRequest;
import com.example.sms.response.SchoolClassResponse;
import com.example.sms.util.requestType.SchoolClassRequestType;
import com.example.sms.validation.AcademicYearValidation;
import com.example.sms.validation.BranchValidation;
import com.example.sms.validation.SchoolClassValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling the update of school classes.
 * It implements the SchoolClassOperation interface to define the specific
 * operation for updating a school class.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateSchoolClassService implements SchoolClassOperation{
    
    /** Validation service for verifying the existence and validity
     *  of SchoolClass entities. */
    private final SchoolClassValidation schoolClassValidation;
    
    /** Mapper for converting between SchoolClassRequest and
     * SchoolClass entities. */
    private final SchoolClassMapper schoolClassMapper;
    
    /** Repository for performing CRUD operations on SchoolClass entities. */
    private final SchoolClassRepository schoolClassRepository;
    
    /** Validation service for verifying the existence and validity
     *  of Branch entities. */
    private final BranchValidation branchValidation;
    
    /** Validation service for verifying the existence and validity
     *  of AcademicYear entities. */
    private final AcademicYearValidation academicYearValidation;
    
    /** List to hold AcademicYear entities associated with the branch. */
    private final List<AcademicYear> academicYearList = new ArrayList<>();
    
    /**
     * Returns the type of school class request this service handles,
     * which is UPDATE.
     *
     * @return SchoolClassRequestType.UPDATE
     */
    @Override
    public SchoolClassRequestType getRequestType() {
        return SchoolClassRequestType.UPDATE;
    }
    
    /**
     * Performs the operation to update an existing school class based on
     * the provided request.
     * It validates the existence of the school class, verifies the associated
     * branch and academic year, updates the entity, saves it to the repository,
     * and returns a response.
     *
     * @param request The SchoolClassRequest containing the details of the
     * school class to be updated.
     * @return A SchoolClassResponse indicating the success of the operation.
     * @throws AcademicYearIdNotFoundException if the academic year ID is not found
     * in the branch or if it is not valid.
     */
    @Override
    public SchoolClassResponse performOperation(SchoolClassRequest request) {
        
        log.info("Updating School class Starting");
        
        String schoolClassId = request.getId();
        
        SchoolClass schoolClass = schoolClassValidation
                .findById(schoolClassId);
        
        log.info("School class with id: {} found successfully",
                schoolClassId);
        
        String branchId = request.getBranchId();
        
        Branch branch = branchValidation.findById(
                branchId);
        
        
        log.info("Branch with id: {} found successfully",
                branchId);
        
        for(AcademicYear academicYear : branch.getAcademicYearId()){
            
            String academicYearIdFromBranch = academicYear.getId();
            
            if(academicYearIdFromBranch != null &&
                    !academicYearIdFromBranch.isEmpty()){
                
                 AcademicYear academicYear1 = academicYearValidation.findById(
                        academicYearIdFromBranch);
                 
                 academicYearList.add(academicYear1);
                 
            }else {
                throw new AcademicYearIdNotFoundException(
                        "Academic year ID not found in branch with id: "
                                + branchId);
            }
            
            
        }
        
        AcademicYear academicYear = academicYearList.stream()
                .filter(x -> x.getId().equals(
                        request.getAcademicYearId()))
                .findFirst()
                .orElseThrow(() -> new AcademicYearIdNotFoundException(
                        "Academic year ID: " + request.getAcademicYearId()
                                + " not found in branch with id: " + branchId));
        
        SchoolClass update = schoolClassMapper.toUpdate(
                request, schoolClass, branch, academicYear);
        
        schoolClassRepository.save(update);
        
        return SchoolClassResponse.builder()
                .message("School class updated successfully")
                .build();
    }
}
