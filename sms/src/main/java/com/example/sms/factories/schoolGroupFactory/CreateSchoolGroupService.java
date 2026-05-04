package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.exception.SchoolGroupFailedException;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.SchoolGroupRepository;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.requestType.SchoolGroupRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CreateSchoolGroupService implements SchoolGroupOperations {
    
    private final SchoolGroupRepository schoolGroupRepository;
    
    private final SchoolGroupMapper schoolGroupMapper;
    
    
    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.CREATE;
    }
    
    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        log.info("Performing school group operation for request: {}",
                request);
        
        SchoolGroup entity = schoolGroupMapper.toEntity(request);
        
        try {
            schoolGroupRepository.save(entity);
            
        }catch (Exception e){
            throw new SchoolGroupFailedException(
                    "Name, mobile number and  Email Already Exists : "
                            + request.getName());
        }
        
        return SchoolGroupResponse.builder()
                .message("School group created successfully")
                        .build();
    }
}