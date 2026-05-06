package com.example.sms.validationImpl;

import com.example.sms.entity.Branch;
import com.example.sms.exception.BranchIdNotFoundException;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.repository.BranchRepository;
import com.example.sms.validation.BranchValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class BranchValidationImpl implements BranchValidation {
    
    private final BranchRepository branchRepository;
    
    @Override
    public Branch findById(String id) {
        
        return branchRepository.findById(id).orElseThrow(() ->
                new BranchIdNotFoundException(
                        "Branch with id " + id + " not found"));
    }
    
    @Override
    public void delete(Branch branch) {
        
        try {
            branchRepository.delete(branch);
        }catch (Exception e){
            throw new DeleteFailedException("Failed to delete Branch with name "
                    + branch.getName() + ": " + e.getMessage());
        }
    }
}
