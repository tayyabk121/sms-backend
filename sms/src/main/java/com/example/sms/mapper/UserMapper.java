package com.example.sms.mapper;

import com.example.sms.model.Branch;
import com.example.sms.model.SchoolGroup;
import com.example.sms.model.User;
import com.example.sms.request.UserRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.response.UserResponse;

import java.util.Optional;

/**
 * Mapper interface for converting between User entities and their
 * corresponding request and response objects. This interface defines methods
 * for mapping UserRequest to User entity, User entity to UserResponse,
 * and updating an existing User entity with data from a UserRequest.
 */
public interface UserMapper {
   
    /** Converts a UserRequest object to a User entity.
     * @param request the UserRequest object containing the data to be mapped
     * @return a User entity populated with data from the UserRequest
     */
    User toEntity(Optional<User> user,
            SchoolGroup schoolGroup,
                  Branch branch,
                  String passwordHash,
                  UserRequest request);
    
    /** Converts a User entity to a UserResponse object.
     * @param user the User entity to be mapped
     * @return a UserResponse object populated with data from the User entity
     */
    UserResponse toResponse(User user,
                            SchoolGroupResponse schoolGroupResponse,
                            BranchResponse branchResponse);
    
    /** Updates an existing User entity with data from a UserRequest.
     * @param user the existing User entity to be updated
     * @param request the UserRequest object containing the new data for the User entity
     * @return the updated User entity with data from the UserRequest
     */
    User toUpdate(User user, UserRequest request);
}
