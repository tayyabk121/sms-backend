package com.example.sms.factories.userFactory;

import com.example.sms.exception.UserNotFoundException;
import com.example.sms.helper.BranchHelper;
import com.example.sms.helper.SchoolGroupHelper;
import com.example.sms.mapper.UserMapper;
import com.example.sms.model.Branch;
import com.example.sms.model.SchoolGroup;
import com.example.sms.model.User;
import com.example.sms.repository.UserRepository;
import com.example.sms.request.UserRequest;
import com.example.sms.response.UserResponse;
import com.example.sms.util.RequestType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for creating new users in the system.
 * Implements the UserOperations interface to define the specific operation
 * for user creation.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CreateUserService implements UserOperations{
   
    /**
     * Mapper for converting between User entities and their corresponding
     * request and response objects.
     */
    private final UserMapper userMapper;
    
    /**
     * Helper for retrieving SchoolGroup entities from the database.
     */
    private final SchoolGroupHelper schoolGroupHelper;
    
    /**
     * Helper for retrieving Branch entities from the database.
     */
    private final BranchHelper branchHelper;
    
    /**
     * Repository for performing CRUD operations on User entities.
     */
    private final UserRepository userRepository;
    
    /**
     * Returns the type of request this service handles, which is CREATE.
     *
     * @return RequestType.CREATE
     */
    @Override
    public RequestType getRequestType() {
        return RequestType.CREATE;
    }
    
    /**
     * Performs the operation of creating a new user based on the provided
     * UserRequest. It validates the request, retrieves necessary entities,
     * encodes the password, and saves the new user to the database.
     *
     * @param userRequest The request containing user details for creation.
     * @return UserResponse indicating the result of the operation.
     * @throws UserNotFoundException if the user cannot be found by phone number or email.
     */
    @Override
    public UserResponse performOperation(UserRequest userRequest) {
        
        SchoolGroup schoolGroup = schoolGroupHelper.findById(
                userRequest.getSchoolGroupId());
        
        Branch branch = branchHelper.findById(userRequest.getBranchId());
        
        String password = passwordEncoder().encode(
                userRequest.getPasswordHash());
        
        Optional<User> byPhoneNumber = userRepository.findByPhoneNumber(
                userRequest.getPhoneNumber());
        
        Optional<User> byEmail = userRepository.findByEmail(
                userRequest.getEmail());
        
        
        User user;
        
        if(userRequest.getPhoneNumber() == null
                && userRequest.getEmail() == null){
            
            throw new UserNotFoundException(
                    "User with phone Number & email is Null");
            
        } else if(byPhoneNumber.isPresent()){
            
            user = userMapper.toEntity(byPhoneNumber, schoolGroup, branch,
                    password,userRequest);
            
        } else if (byEmail.isPresent()) {
            
            user = userMapper.toEntity(byEmail, schoolGroup, branch,
                    password, userRequest);
            
        } else {
            throw new UserNotFoundException("User with phone number "
                    + userRequest.getPhoneNumber()
                    + " or email " + userRequest.getEmail() + " Not Found ");
        }
        
        userRepository.save(user);
        
        return UserResponse.builder()
                .message("User created successfully")
                .build();
    }
    
    /**
     * Creates and returns a PasswordEncoder instance for encoding passwords.
     * This implementation uses BCryptPasswordEncoder for secure password hashing.
     *
     * @return PasswordEncoder instance
     */
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
