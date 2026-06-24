package com.example.sms.serviceImpl;

import com.example.sms.exception.MailSendFailedException;
import com.example.sms.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    
    private final JavaMailSender javaMailSender;
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    @Override
    public void mailSender(String to, String subject, String text){
        
        log.info("Sending email using JavaMailSender");
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            javaMailSender.send(mailMessage);
            log.info("Email sent successfully to {}", to);
        }catch (Exception e){
            throw new MailSendFailedException("Failed to send email "
                    + e.getMessage());
        }
    }
}
