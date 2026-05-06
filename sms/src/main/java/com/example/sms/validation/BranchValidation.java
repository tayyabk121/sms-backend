package com.example.sms.validation;

import com.example.sms.entity.Branch;

public interface BranchValidation {
    
    Branch findById(String id);
    
    void delete(Branch branch);
}
