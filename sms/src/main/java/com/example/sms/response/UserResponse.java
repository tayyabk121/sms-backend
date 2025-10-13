package com.example.sms.response;

import com.example.sms.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

//    private String phone;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
//        this.phone = user.getPhone();
    }
}
