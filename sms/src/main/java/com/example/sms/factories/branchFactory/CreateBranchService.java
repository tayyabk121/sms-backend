package com.example.sms.factories.branchFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.repository.BranchRepository;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import com.example.sms.validation.SchoolGroupValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CreateBranchService implements BranchOperation{
    
    private final BranchMapper branchMapper;
    
    private final SchoolGroupValidation schoolGroupValidation;
    
    private final BranchRepository branchRepository;
    
    
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.CREATE;
    }
    
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {
        
        SchoolGroup schoolGroup = schoolGroupValidation
                .findById(branchRequest.getSchoolGroupId());
        
        Branch entity = branchMapper.toEntity(
                branchRequest, schoolGroup);
        
        
        branchRepository.save(entity);
        
        return BranchResponse.builder()
                .message("Branch created successfully")
                .build();
    }
}
