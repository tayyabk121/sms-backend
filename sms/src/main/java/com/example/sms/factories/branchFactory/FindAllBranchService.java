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

@Log4j2
@Service
@RequiredArgsConstructor
public class FindAllBranchService implements BranchOperation{

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;

    @Override
    public BranchRequestType getRequestType() {
        return BranchRequestType.FIND_ALL;
    }

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
