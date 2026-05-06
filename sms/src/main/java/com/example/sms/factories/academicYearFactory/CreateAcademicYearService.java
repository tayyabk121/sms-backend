package com.example.sms.factories.academicYearFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.exception.BranchIdNotFoundException;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.repository.AcademicYearRepository;
import com.example.sms.request.AcademicYearRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.util.requestType.AcademicYearRequestType;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CreateAcademicYearService implements AcademicYearOperations {

    private final AcademicYearRepository academicYearRepository;
    private final AcademicYearMapper academicYearMapper;
    private final BranchValidation branchValidation;

    @Override
    public AcademicYearRequestType getRequestType() {
        return AcademicYearRequestType.CREATE;
    }

    @Override
    public AcademicYearResponse performOperation(AcademicYearRequest request) {
            log.info("Performing academic year operation for request: {}",
                    request);
        
        Branch branch = branchValidation.findById(request.getBranchId());
            
            AcademicYear entity = academicYearMapper.toEntity(
                    request, branch);
            
            academicYearRepository.save(entity);
            
                return AcademicYearResponse.builder()
                        .message("Academic year created successfully")
                        .isCurrent(entity.isCurrent())
                        .build();
    }
}
