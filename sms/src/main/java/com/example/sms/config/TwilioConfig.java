package com.example.sms.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** Configuration class for Twilio integration. This class is responsible for
 * loading the Twilio account SID, authentication token, and WhatsApp number
 * from the application properties. It also initializes the Twilio client using
 * the provided credentials. */
@Configuration
@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
public class TwilioConfig {
    
    /** The Twilio account SID, injected from application properties. */
    private String accountSid;
    
    /** The Twilio authentication token, injected from application properties. */
    private String authToken;
    
    /** The WhatsApp number to send messages from, injected from application properties. */
    private String whatsappNumber;
    
    /** Initializes the Twilio client with the provided account SID and authentication token. */
    @PostConstruct
    public void init(){
        Twilio.init(accountSid, authToken);
    }
}
