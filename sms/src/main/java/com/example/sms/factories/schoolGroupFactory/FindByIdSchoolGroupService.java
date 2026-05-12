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

/**
 * Service class responsible for handling the operation of finding
 * a school group by its ID.
 * Implements the SchoolGroupOperations interface to define the specific
 * operation type and logic.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindByIdSchoolGroupService implements SchoolGroupOperations {

    /** Validation component for ensuring the integrity of school group data. */
    private final SchoolGroupValidation schoolGroupValidation;
    
    /** Mapper for converting between SchoolGroupRequest and
     * SchoolGroup entities. */
    private final SchoolGroupMapper schoolGroupMapper;

    /**
     * Returns the type of school group request this service handles,
     * which is FIND_BY_ID.
     *
     * @return SchoolGroupRequestType.FIND_BY_ID
     */
    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.FIND_BY_ID;
    }

    /**
     * Performs the operation to find a school group by its ID based on
     * the provided request.
     * It validates the existence of the school group, retrieves it from
     * the repository, and returns a response with the school group details.
     *
     * @param request The SchoolGroupRequest containing the ID of the
     * school group to be found.
     * @return A SchoolGroupResponse containing the details of the found
     * school group.
     */
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
