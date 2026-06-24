package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.model.SchoolGroup;
import com.example.sms.exception.CreateFailedException;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.SchoolGroupRepository;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the creation of school groups.
 * Implements the SchoolGroupOperations interface to define the specific
 * operation type and logic.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class CreateSchoolGroupService implements SchoolGroupOperations {
    
    /** Repository for performing CRUD operations on SchoolGroup entities. */
    private final SchoolGroupRepository schoolGroupRepository;
    
    /** Mapper for converting between SchoolGroupRequest and SchoolGroup entities. */
    private final SchoolGroupMapper schoolGroupMapper;
    
    
    /**
     * Returns the type of school group request this service handles,
     * which is CREATE.
     *
     * @return RequestType.CREATE
     */
    @Override
    public RequestType getSchoolGroupRequestType() {
        return RequestType.CREATE;
    }
    
    /**
     * Performs the operation to create a new school group based on
     * the provided request.
     * It maps the request to an entity, saves it to the repository,
     * and returns a response.
     *
     * @param request The SchoolGroupRequest containing the details of the
     * school group to be created.
     * @return A SchoolGroupResponse indicating the success of the operation.
     * @throws CreateFailedException if there is an error during the
     * creation process, such as duplicate entries.
     */
    @Override
    public SchoolGroupResponse performOperation(SchoolGroupRequest request) {
        log.info("Performing school group operation for request: {}",
                request);
        
        SchoolGroup entity = schoolGroupMapper.toEntity(request);
        
        try {
            schoolGroupRepository.save(entity);
            
        }catch (Exception e){
            throw new CreateFailedException(
                    "Name, mobile number and Email Already Exists : "
                            + request.getName());
        }
        
        return SchoolGroupResponse.builder()
                .message("School group created successfully")
                        .build();
    }
}