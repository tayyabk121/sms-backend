package com.example.sms.helper;

import com.example.sms.model.Branch;

/**
 * Interface for validating Branch entities.
 */
public interface BranchHelper {
   
    /**
     * Validates the given Branch entity.
     * findById method is used to check if the branch with the given
     * ID exists in the database. If the branch does not exist, an exception
     * is thrown.
     * @param id the Branch entity to validate
     * @throws IllegalArgumentException if the branch is null or if the
     * branch with the given ID does not exist
     */
    Branch findById(String id);
    
    /**
     * Validates the given Branch entity for creation.
     * This method checks if the branch is null and if a branch with the same
     * ID already exists in the database. If either condition is true, an
     * exception is thrown.
     * @param branch the Branch entity to validate
     * @throws IllegalArgumentException if the branch is null or if a branch
     * with the same ID already exists
     */
    void delete(Branch branch);
}
