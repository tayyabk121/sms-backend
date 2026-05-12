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

/**
 * Service class responsible for handling the update of school groups.
 * Implements the SchoolGroupOperations interface to define the specific
 * operation type and logic.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class UpdateSchoolGroupService implements SchoolGroupOperations{

    /** Repository for performing CRUD operations on SchoolGroup entities. */
    private final SchoolGroupRepository schoolGroupRepository;

    /** Mapper for converting between SchoolGroupRequest and
     * SchoolGroup entities. */
    private final SchoolGroupMapper schoolGroupMapper;
    
    /** Validation component for ensuring the integrity of SchoolGroup data. */
    private final SchoolGroupValidation schoolGroupValidation;

    /**
     * Returns the type of school group request this service handles,
     * which is UPDATE.
     *
     * @return SchoolGroupRequestType.UPDATE
     */
    @Override
    public SchoolGroupRequestType getSchoolGroupRequestType() {
        return SchoolGroupRequestType.UPDATE;
    }

    /**
     * Performs the operation to update an existing school group based on
     * the provided request.
     * It retrieves the existing entity, maps the request to an updated
     * entity, saves it to the repository, and returns a response.
     *
     * @param request The SchoolGroupRequest containing the details of the
     * school group to be updated.
     * @return A SchoolGroupResponse indicating the success of the operation.
     */
    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        String schoolGroupId = request.getSchoolGroupId();
        
            log.info("Starting update process for SchoolGroup with ID: {}",
                    schoolGroupId);
            
            SchoolGroup schoolGroup = schoolGroupValidation
                    .findById(schoolGroupId);
            
        SchoolGroup update = schoolGroupMapper.toUpdate(
                request, schoolGroup);
        
        schoolGroupRepository.save(update);
        
        return schoolGroupMapper.toResponse(update);
        

    }
}
