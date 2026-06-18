package com.example.sms.serviceImpl;

import com.example.sms.service.WhatsappNotificationService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class WhatsappNotificationServiceImpl implements
        WhatsappNotificationService {
    
    @Value("${twilio.whatsapp.from}")
    private String whatsappNumber;
    
    @Override
    public void sentOtp(String phoneNumber, String otp) {
        
        try{
            String message = "Your School Management System OTP is : " + otp;
            
            Message.creator(
                    new PhoneNumber("whatsapp:" + phoneNumber),
                    new PhoneNumber(whatsappNumber),
                    message
            ).create();
            
        }catch (Exception e){
            log.error("Failed to send OTP via WhatsApp to {}: {}",
                    phoneNumber, e.getMessage());
            
            throw new RuntimeException(
                    "Failed to send OTP via WhatsApp. Please try again later.");
        }
    
    }
}
