package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.SchoolGroupRepository;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.requestType.SchoolGroupRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllSchoolGroupService implements SchoolGroupOperations{

    private final SchoolGroupRepository schoolGroupRepository;

    private final SchoolGroupMapper schoolGroupMapper;

    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.FIND_ALL;
    }

    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        
        log.info("Starting process to find all School Groups");
        
        List<SchoolGroup> list = schoolGroupRepository.findAll();
        
        log.info("Total School Groups found: {}", list.size());
        
        List<SchoolGroupResponse> studentResponseList =
                list.stream().map(schoolGroupMapper::toResponse).toList();
        
        return SchoolGroupResponse.builder()
                .schoolResponseList(studentResponseList)
                .build();
    }
}
