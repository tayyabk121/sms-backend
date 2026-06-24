package com.example.sms.service;

public interface EmailService {
    
    void mailSender(String to, String subject, String text);
}
