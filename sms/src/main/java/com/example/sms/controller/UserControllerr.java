package com.example.sms.controller;

import com.example.sms.entity.User;
import com.example.sms.request.UserRequest;
import com.example.sms.response.UserResponse;
import com.example.sms.security.JwtUtil;
import com.example.sms.security.UserDetailService;
import com.example.sms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserControllerr {
    @Autowired
    private final UserService userService;
    private final UserDetailService userDetailService;
    private final AuthenticationManager authenticationManager;
    private  final JwtUtil jwtUtil;

    @PostMapping("/signUp")
    public String signUp (@RequestBody UserRequest user){
        userService.signUp(user);
        return "Registered Successfully";
    }
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserRequest user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getEmail());
            String jwt = jwtUtil.genrateToken(user.getEmail());
            log.info("Jwt "+jwt);
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Incorrect Email or Password ", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getAll")
    public List<UserResponse> findAll (){
        return userService.findAll();
    }

    @PostMapping("/logout")
    public String logout (@RequestHeader ("Authorization") String token){
        if(token.startsWith("Bearer ")){
            token = token.substring(7);
        }
        userService.logout(token);
        return "Logout Successfully";

    }

}
