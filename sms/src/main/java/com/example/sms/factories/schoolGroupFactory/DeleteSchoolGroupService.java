package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.entity.SchoolGroup;
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
public class DeleteSchoolGroupService implements SchoolGroupOperations{

    private final SchoolGroupValidation schoolGroupValidation;

    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.DELETE;
    }

    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        
        String schoolGroupId = request.getSchoolGroupId();
        
        log.info("Attempting to delete School Group with ID: {}",
                schoolGroupId);
        
        SchoolGroup schoolGroup = schoolGroupValidation
                .findById(schoolGroupId);
        
        schoolGroupValidation.delete(schoolGroup);
        
        log.info("School Group with ID: {} deleted successfully",
                schoolGroupId);
        

        return SchoolGroupResponse.builder()
                .message("School Group deleted successfully")
                .build();
    }
}
