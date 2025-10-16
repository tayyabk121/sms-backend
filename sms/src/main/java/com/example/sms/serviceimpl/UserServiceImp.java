package com.example.sms.serviceimpl;

import com.example.sms.entity.Jwt;
import com.example.sms.entity.User;
import com.example.sms.repository.JwtRepository;
import com.example.sms.repository.UserRepository;
import com.example.sms.request.UserRequest;
import com.example.sms.response.UserResponse;
import com.example.sms.security.UserSecurity;
import com.example.sms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;
    private final JwtRepository jwtRepository;

    @Override
    public void signUp(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(userSecurity.passwordEncoder().encode(userRequest.getPassword()));
//        user.setPhone(userRequest.getPhone());
        user.setRole(userRequest.getRole().toUpperCase());
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> all = userRepository.findAll();
       return all.stream().map(UserResponse::new).toList();
    }

    @Override
    public void logout(String token) {
        Jwt jwt = new Jwt();
        jwt.setJwt(token);
        jwtRepository.save(jwt);
    }

    @Scheduled(fixedRate = 60000)
    public String logoutScheduled (){
        List<Jwt> all = jwtRepository.findAll();
        for(Jwt list: all){
            LocalTime time = list.getGeneratedAt().toLocalTime().plusHours(7);
            if(LocalTime.now().isAfter(time)){
                jwtRepository.delete(list);
            }
        }
        return "Scheduled is Running";
    }
}
