package com.example.sms.kafkaListeners;

import com.example.sms.kafkaEvent.WhatsappEvent;
import com.example.sms.service.WhatsappNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

/** Kafka listener responsible for handling WhatsApp notification events.
 * Listens for
 * events containing the recipient's phone number and OTP, and delegates the task
 * of sending the OTP via WhatsApp to the WhatsappNotificationService. */
@Log4j2
@Component
@RequiredArgsConstructor
public class WhatsappNotificationListener{
   
    /** Service responsible for sending WhatsApp notifications. */
    private final WhatsappNotificationService whatsappNotificationService;
    
    /** Handles the event of sending an OTP to a WhatsApp number.
     * @param whatsappEvent the event containing the phone number and OTP to be sent */
    @RetryableTopic(attempts = "3")
    @KafkaListener(topics = "whatsapp-otp-topic", groupId = "otp-group")
    public void sentOtp(final WhatsappEvent whatsappEvent){
        try {
            whatsappNotificationService.sentOtp(whatsappEvent.getPhoneNumber(),
                    whatsappEvent.getOtp());
            log.info("OTP sent to WhatsApp number: {}",
                    whatsappEvent.getPhoneNumber());
        }catch (Exception e){
            log.error("Failed to send OTP to WhatsApp number: {}",
                    whatsappEvent.getPhoneNumber(), e);
            throw e;
        }
    }
}
