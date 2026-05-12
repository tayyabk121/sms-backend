package com.example.sms.factories.branchFactory;

import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolGroup;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.repository.BranchRepository;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import com.example.sms.validation.SchoolGroupValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling the creation of branches.
 * It implements the BranchOperation interface to define the specific
 * operation for creating a branch.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CreateBranchService implements BranchOperation{
    
    /** Mapper for converting between BranchRequest and Branch entities. */
    private final BranchMapper branchMapper;
    
    /** Validation service for verifying the existence and validity of
     *  SchoolGroup entities. */
    private final SchoolGroupValidation schoolGroupValidation;
    
    /** Repository for performing CRUD operations on Branch entities. */
    private final BranchRepository branchRepository;
    
    /**
     * Returns the type of branch request this service handles,
     * which is CREATE.
     *
     * @return BranchRequestType.CREATE
     */
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.CREATE;
    }
    
    /**
     * Performs the operation to create a new branch based on the provided
     * request. It validates the existence of the associated school group,
     * converts the request to a Branch entity, saves it to the repository,
     * and returns a response indicating the success of the operation.
     *
     * @param branchRequest The BranchRequest containing the details for
     * creating a new branch.
     * @return A BranchResponse indicating the success of the operation.
     */
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {
        
        SchoolGroup schoolGroup = schoolGroupValidation
                .findById(branchRequest.getSchoolGroupId());
        
        Branch entity = branchMapper.toEntity(
                branchRequest, schoolGroup);
        
        
        branchRepository.save(entity);
        
        return BranchResponse.builder()
                .message("Branch created successfully")
                .build();
    }
}
