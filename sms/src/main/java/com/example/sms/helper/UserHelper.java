package com.example.sms.helper;

import com.example.sms.model.User;

/**
 * Interface for validating operations related to User entities.
 */
public interface UserHelper {
   
    /** Validates finding a User by its ID.
     * @param id the ID of the User to find
     * @return the User entity if found,
     * otherwise null or an exception may be thrown
     */
    User findById(String id);
    
    /** Validates saving a User entity.
     * @param user the User entity to save
     */
    void delete(User user);
}
