package com.example.sms.helperImpl;

import com.example.sms.helper.AuthHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Random;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthHelperImpl implements AuthHelper {
    
    @Override
    public String otpGenerate() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        log.info("Generated OTP: {}", otp);
        return String.valueOf(otp);
    }
}
