package com.example.sms.helperImpl;

import com.example.sms.exception.DeleteFailedException;
import com.example.sms.exception.IdNotFoundException;
import com.example.sms.helper.UserHelper;
import com.example.sms.model.User;
import com.example.sms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * Implementation of the UserHelper interface for validating and managing
 * User entities. This class provides methods to find a User by its ID and to
 * delete a User, with appropriate exception handling for cases where the entity
 * is not found or deletion fails.
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserHelperImp implements UserHelper {
   
    /** Repository for accessing User entities in the database. */
    private final UserRepository userRepository;
    
    /** Method to find a User by its ID. This method retrieves a User from the
     * database using its ID and returns it. If the User is not found, it throws
     * an IdNotFoundException to indicate that the User with the specified ID does
     * not exist.
     *
     * @param id the ID of the User to find
     * @return the User entity if found
     * @throws IdNotFoundException if no User with the specified ID exists
     */
    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()->
                new IdNotFoundException("User with id " + id + " not found"));
    }
    
    @Override
    public void delete(User user) {
        try{
            userRepository.delete(user);
        }catch (Exception e){
            throw new DeleteFailedException("Failed to delete user with id "
                    + user.getId() + ": " + e.getMessage());
        }
    }
}
