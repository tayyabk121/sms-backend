package com.example.sms.factories.branchFactory;

import com.example.sms.entity.AcademicYear;
import com.example.sms.entity.Branch;
import com.example.sms.mapper.AcademicYearMapper;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.mapper.SchoolGroupMapper;
import com.example.sms.repository.BranchRepository;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.AcademicYearResponse;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.util.requestType.BranchRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling the retrieval of all branches.
 * It implements the BranchOperation interface to define the specific
 * operation for finding all branches.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllBranchService implements BranchOperation{

    /** Repository for performing CRUD operations on Branch entities. */
    private final BranchRepository branchRepository;

    /** Mapper for converting between Branch entities and BranchResponse objects. */
    private final BranchMapper branchMapper;
    
    /** Mapper for converting between SchoolGroup entities and
     * SchoolGroupResponse objects, used to include school group details in
     * the branch responses. */
    private final SchoolGroupMapper schoolGroupMapper;
    
    /** Mapper for converting between AcademicYear entities and
     * AcademicYearResponse objects, used to include academic year details in
     * the branch responses. */
    private final AcademicYearMapper academicYearMapper;

    /**
     * Returns the type of branch request this service handles,
     * which is FIND_ALL.
     *
     * @return BranchRequestType.FIND_ALL
     */
    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.FIND_ALL;
    }

    /**
     * Performs the operation to find all branches. It retrieves all Branch
     * entities from the repository, converts them to BranchResponse objects,
     * and returns a response containing the list of branches.
     *
     * @param branchRequest The BranchRequest for finding all branches (not used
     * in this operation).
     * @return A BranchResponse containing the list of all branches.
     */
    @Override
    public BranchResponse performOperation(BranchRequest branchRequest) {

            log.info("Starting process to find all Branches");

            List<Branch> branch = branchRepository.findAll();
        
        List<BranchResponse> branchResponseList = new ArrayList<>();
        
        for (Branch branch1 : branch){
            log.info("Branch found: {}", branch1.getName());
            
            SchoolGroupResponse schoolGroupResponse = schoolGroupMapper
                    .toResponse(branch1.getSchoolGroupId());
            
            List<AcademicYearResponse> academicYearResponseList =
                    new ArrayList<>();
            
            for (AcademicYear academicYear : branch1.getAcademicYearId()){
                log.info("Academic Year found: {}", academicYear.getLabel());
                
                AcademicYearResponse academicYearResponse =
                        academicYearMapper.toResponse(academicYear, null);
                
                academicYearResponseList.add(academicYearResponse);
            }
            
            BranchResponse branchResponse = branchMapper.toResponse(
                    branch1, schoolGroupResponse,
                    academicYearResponseList);
            
            branchResponseList.add(branchResponse);
        }

        return BranchResponse.builder()
                .branchResponseList(branchResponseList)
                .build();
    }
}
