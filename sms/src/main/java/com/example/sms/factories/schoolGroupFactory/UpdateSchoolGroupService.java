package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.SchoolGroupRepository;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.requestType.SchoolGroupRequestType;
import com.example.sms.validation.SchoolGroupValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateSchoolGroupService implements SchoolGroupOperations{

    private final SchoolGroupRepository schoolGroupRepository;

    private final SchoolGroupMapper schoolGroupMapper;
    
    private final SchoolGroupValidation schoolGroupValidation;

    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.UPDATE;
    }

    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        String schoolGroupId = request.getSchoolGroupId();
        
            log.info("Starting update process for SchoolGroup with ID: {}",
                    schoolGroupId);
            
            SchoolGroup schoolGroup = schoolGroupValidation
                    .FindById(schoolGroupId);
        
        SchoolGroup update = schoolGroupValidation.update(
                request, schoolGroup);
        
        return schoolGroupMapper.toResponse(update);
        

    }
}
