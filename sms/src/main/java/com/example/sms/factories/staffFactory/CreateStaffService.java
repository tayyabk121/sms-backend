package com.example.sms.factories.staffFactory;

import com.example.sms.helper.StaffHelper;
import com.example.sms.model.Branch;
import com.example.sms.model.Staff;
import com.example.sms.helper.BranchHelper;
import com.example.sms.mapper.StaffMapper;
import com.example.sms.repository.StaffRepository;
import com.example.sms.request.StaffRequest;
import com.example.sms.response.StaffResponse;
import com.example.sms.util.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the creation of Staff entities in the
 * school management system. This class implements the StaffOperations
 * interface and provides the logic for creating a new Staff member based on the
 * details provided in a StaffRequest. It validates the associated Branch,
 * generates a unique employee number, maps the request to a Staff entity,
 * saves it to the database, and returns a response indicating the success of
 * the operation.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CreateStaffService implements StaffOperations{
    
    /** Mapper for converting between Staff entities and their corresponding request
     * and response objects. */
    private final StaffMapper staffMapper;
    
    /** Helper for performing operations related to Staff entities,
     *  such as generating unique employee numbers. */
    private final StaffHelper staffHelper;
    
    /** Repository for accessing Staff entities in the database, used to save the newly
     * created Staff member. */
    private final StaffRepository staffRepository;
    
    /** Validation service for Branch entities, used to validate the associated
     * Branch when creating a Staff member. */
    private final BranchHelper branchHelper;
    
    /** Method to get the type of request this service handles, which is CREATE in
     * this case. This method is used to identify the type of operation being
     * performed when processing a StaffRequest. */
    @Override
    public RequestType getRequestType() {
        return RequestType.CREATE;
    }
    
    /** Method to perform the create operation for a Staff member based on the provided
     * StaffRequest. This method validates the associated Branch, generates a unique
     * employee number, maps the request to a Staff entity, saves it to the database,
     * and returns a StaffResponse indicating the success of the operation. */
    @Override
    public StaffResponse execute(StaffRequest request) {
        
        log.info("Executing create operation for Staff with name: {}",
                request.getName());
        
        Branch branch = branchHelper.findById(request.getBranchId());
        
        String employeeNo = staffHelper.EmployeeGenerate();
        
        Staff entity = staffMapper.toEntity(request, branch, employeeNo);
        
        staffRepository.save(entity);
        
        log.info("Saving new staff with employee number: {}",
                employeeNo);
        
        return StaffResponse.builder()
                .message("Staff created successfully with employee number: "
                        + employeeNo)
                .build();
    }
}
