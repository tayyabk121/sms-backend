package com.example.sms.mapper;

import com.example.sms.entity.Branch;
import com.example.sms.entity.SchoolGroup;
import com.example.sms.request.BranchRequest;
import com.example.sms.response.BranchResponse;

public interface BranchMapper {
    
    Branch toEntity(BranchRequest request, SchoolGroup schoolGroup);
    
    Branch toUpdate(BranchRequest request, Branch branch,
                    SchoolGroup schoolGroup);
    
    BranchResponse toResponse(Branch branch);
}