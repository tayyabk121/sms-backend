package com.example.sms.mapperImpl;

import com.example.sms.mapper.UserMapper;
import com.example.sms.model.Branch;
import com.example.sms.model.SchoolGroup;
import com.example.sms.model.User;
import com.example.sms.request.UserRequest;
import com.example.sms.response.BranchResponse;
import com.example.sms.response.SchoolGroupResponse;
import com.example.sms.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    
    @Override
    public User toEntity(Optional<User> user1,
                         SchoolGroup schoolGroup,
                         Branch branch,
                         String passwordHash,
                         UserRequest request) {
        
        User user = user1.get();
        
        user.setSchoolGroup(schoolGroup);
                user.setBranch(branch);
                user.setName(request.getName());
                
                if(request.getEmail() != null){
                    user.setEmail(request.getEmail());
                }
                if(request.getPhoneNumber() != null){
                    user.setPhoneNumber(request.getPhoneNumber());
                }
                user.setRole(request.getRole());
                user.setPermissions(request.getPermissions());
                user.setPasswordHash(passwordHash);
                user.setStatus(request.getStatus());
//                user.setStaffRole(request.getStaffRole());
        
        return user;
    }
    
    @Override
    public UserResponse toResponse(User user,
                                   SchoolGroupResponse schoolGroupResponse,
                                   BranchResponse branchResponse) {
        
        return UserResponse.builder()
                .id(user.getId())
                .schoolGroupId(schoolGroupResponse)
                .branchId(branchResponse)
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .permissions(user.getPermissions())
                .status(user.getStatus())
                .build();
    }
    
    @Override
    public User toUpdate(User user, UserRequest request) {
        return null;
    }
}
