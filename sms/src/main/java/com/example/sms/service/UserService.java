package com.example.sms.service;

import com.example.sms.entity.User;
import com.example.sms.request.UserRequest;
import com.example.sms.response.UserResponse;

import java.util.List;

public interface UserService {
    void signUp (User user);
    List<UserResponse> findAll ();
    void logout (String token);
}
