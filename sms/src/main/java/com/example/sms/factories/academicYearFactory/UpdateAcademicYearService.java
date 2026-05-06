package com.example.sms.factories.academicYearFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.requestType.AcademicYearRequestType;
import com.example.sms.validation.AcademicYearValidation;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateAcademicYearService implements AcademicYearOperations{
    
    private final AcademicYearValidation academicYearValidation;
    
    private final AcademicYearMapper academicYearMapper;
    
    private final BranchValidation branchValidation;
    
    private final AcademicYearRepository academicYearRepository;
    
    @Override
    public AcademicYearRequestType getRequestType() {
        return AcademicYearRequestType.UPDATE;
    }
    
    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
        
        String academicYearId = request.getId();
        
        log.info("Starting update process for Academic Year with ID: {}",
                academicYearId);
        
        AcademicYear academicYear = academicYearValidation.findById(
                academicYearId);
        
        log.info("Academic Year found: {}", academicYear.getId());
        
        Branch branch = branchValidation.findById(request.getBranchId());
        
        AcademicYear update = academicYearMapper.toUpdate(
                request, academicYear, branch);
        
        academicYearRepository.save(update);
        
        return AcademicYearResponse.builder()
                .message("Academic Year updated successfully")
                .isCurrent(update.isCurrent())
                .build();
    }
}
