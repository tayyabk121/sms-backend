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

/**
 * Service class responsible for handling the operation of finding all
 * school groups.
 * Implements the SchoolGroupOperations interface to define the specific
 * operation type and logic.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllSchoolGroupService implements SchoolGroupOperations{

    /** Repository for performing CRUD operations on SchoolGroup entities. */
    private final SchoolGroupRepository schoolGroupRepository;

    /** Mapper for converting between SchoolGroup entities and
     *  SchoolGroupResponse objects. */
    private final SchoolGroupMapper schoolGroupMapper;

    /**
     * Returns the type of school group request this service handles,
     * which is FIND_ALL.
     *
     * @return SchoolGroupRequestType.FIND_ALL
     */
    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.FIND_ALL;
    }

    /**
     * Performs the operation to find all school groups. It retrieves
     * all school group entities from the repository,
     * maps them to response objects, and returns a response containing
     * the list of school groups.
     *
     * @param request The SchoolGroupRequest (not used in this operation
     *               but included for consistency).
     * @return A SchoolGroupResponse containing a list of all school groups.
     */
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
