package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.SchoolGroupMapper;
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
public class FindByIdSchoolGroupService implements SchoolGroupOperations {

    private final SchoolGroupValidation schoolGroupValidation;

    private final SchoolGroupMapper schoolGroupMapper;

    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.FIND_BY_ID;
    }

    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        
        String schoolGroupId = request.getSchoolGroupId();
        
            log.info("Starting process to find School Group with name: {}",
                    schoolGroupId);
            
            SchoolGroup schoolGroup = schoolGroupValidation
                    .findById(schoolGroupId);
            
            log.info("School Group found: {}", schoolGroup.getName());
            
            return schoolGroupMapper.toResponse(schoolGroup);
            
    }
}
