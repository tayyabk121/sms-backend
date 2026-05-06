package com.example.sms.factories.branchFactory;

import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.repository.BranchRepository;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import com.example.sms.validation.AcademicYearValidation;
import com.example.sms.validation.BranchValidation;
import com.example.sms.validation.SchoolGroupValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateBranchService implements BranchOperation{
    
    private final BranchMapper branchMapper;
    
    private final BranchValidation branchValidation;
    
    private final BranchRepository branchRepository;
    
    private final AcademicYearMapper academicYearMapper;
    
    private final AcademicYearValidation academicYearValidation;
    
    private final SchoolGroupValidation schoolGroupValidation;
    
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.UPDATE;
    }
    
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {
        
        String branchId = branchRequest.getBranchId();
        
        log.info("Starting update process for Branch with ID: {}",
                branchId);
        
        Branch branch = branchValidation.findById(branchId);
        
        SchoolGroup schoolGroupId = schoolGroupValidation.findById(
                branchRequest.getSchoolGroupId());
        
        branchMapper.toUpdate(branchRequest, branch, schoolGroupId);
        
        branchRepository.save(branch);
        
        return BranchResponse.builder()
                .message("Branch updated successfully")
                .build();
    }
}
