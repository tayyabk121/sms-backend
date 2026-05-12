package com.example.sms.factories.branchFactory;

import com.example.sms.entity.Branch;
import com.example.sms.mapper.BranchMapper;
import com.example.sms.repository.BranchRepository;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.util.requestType.BranchRequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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

        List<BranchResponse> list = branch.stream().map(
                branchMapper::toResponse).toList();
        
        

        return BranchResponse.builder()
                .branchResponseList(list)
                .build();
    }
}
