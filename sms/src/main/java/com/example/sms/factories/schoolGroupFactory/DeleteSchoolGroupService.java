package com.example.sms.factories.schoolGroupFactory;

import com.example.sms.model.SchoolGroup;
import com.example.sms.request.SchoolGroupRequest;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.RequestType;
import com.example.sms.helper.SchoolGroupHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the deletion of school groups.
 * Implements the SchoolGroupOperations interface to define the specific
 * operation type and logic for deleting a school group.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class DeleteSchoolGroupService implements SchoolGroupOperations{

    /** Validation component for performing checks and operations
     * related to SchoolGroup entities. */
    private final SchoolGroupHelper schoolGroupValidation;

    /** RequestType that this service handles, which is DELETE. */
    @Override
    public RequestType getSchoolGroupRequestType() {
        return RequestType.DELETE;
    }

    /**
     * Performs the operation to delete a school group based on
     * the provided request.
     * It validates the existence of the school group, deletes it,
     * and returns a response.
     *
     * @param request The SchoolGroupRequest containing the ID of
     * the school group to be deleted.
     * @return A SchoolGroupResponse indicating the success of
     * the deletion operation.
     */
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
