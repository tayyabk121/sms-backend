package com.example.sms.helperImpl;

import com.example.sms.model.Branch;
import com.example.sms.exception.IdNotFoundException;
import com.example.sms.exception.DeleteFailedException;
import com.example.sms.repository.BranchRepository;
import com.example.sms.helper.BranchHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the BranchValidation interface that provides methods
 * for validating and managing Branch entities. This class interacts with
 * the BranchRepository to perform database operations and includes error
 * handling for cases where a branch is not found or when deletion fails.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class BranchHelperImpl implements BranchHelper {
   
    /** The BranchRepository is used to perform database operations related to
     * Branch entities. It provides methods for finding, saving, and deleting
     * branches in the database. By injecting this repository, we can ensure that
     * all interactions with the database are handled through a consistent and
     * well-defined interface, allowing for better maintainability and scalability
     * of the application. */
    private final BranchRepository branchRepository;
    
    /** The findById method retrieves a Branch entity from the database based on
     * the provided ID. If the branch is not found, it throws a
     * BranchIdNotFoundException with a descriptive error message. This method
     * ensures that the caller receives clear feedback when a branch with the
     * specified ID does not exist in the database. */
    @Override
    public Branch findById(String id) {
        
        return branchRepository.findById(id).orElseThrow(() ->
                new IdNotFoundException(
                        "Branch with id " + id + " not found"));
    }
    
    /** The delete method removes a Branch entity from the database. It takes a
     * Branch object as a parameter and attempts to delete it using the
     * BranchRepository. If the deletion process encounters any exceptions, it
     * catches the exception and throws a DeleteFailedException with a descriptive
     * error message that includes the name of the branch and the original error
     * message. This method ensures that any issues during deletion are properly
     * communicated to the caller, allowing for better error handling and user
     * feedback. */
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
