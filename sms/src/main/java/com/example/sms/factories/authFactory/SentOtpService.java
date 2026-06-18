package com.example.sms.factories.authFactory;

import com.example.sms.service.EmailService;
import com.example.sms.exception.InvalidAuthenticationRequestException;
import com.example.sms.helper.AuthHelper;
import com.example.sms.kafkaEvent.WhatsappEvent;
import com.example.sms.request.AuthenticationRequest;
import com.example.sms.response.AuthenticationResponse;
import com.example.sms.util.AuthenticationRequestType;
import com.example.sms.validation.AuthValidation;
import com.example.sms.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * SentOtpService is a service class responsible for handling the operation of sending
 * OTP (One-Time Password) to users during the authentication process. It implements
 * the AuthenticationOperation interface, which defines the contract for performing
 * operations related to user authentication.
 *
 * The service validates the phone number provided in the authentication request,
 * checks for OTP request limits to prevent abuse, generates an OTP, stores it in
 * Redis with an expiration time, and sends the OTP to the user via a Kafka event.
 *
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class SentOtpService implements AuthenticationOperation{
   
    /** Kafka topic for sending OTP-related events. */
    private static final String OTP_TOPIC = "whatsapp-otp-topic";
    
    /** Redis key prefix for tracking OTP request rates per phone number. */
    private static final String OTP_RATE_KEY = "otp:rate:";
    
    /** Maximum number of OTP requests allowed within the defined time window. */
    private static final int OTP_LIMIT = 3;
    
    /** Time window in minutes for OTP request rate limiting. */
    private static final int WINDOW_MINUTES = 10;
    
    /** Redis key prefix for storing OTPs associated with phone numbers. */
    private static final String OTP_KEY = "otp:";
    
    /** Expiry time for OTPs in minutes. */
    private static final int EXPIRY_TIME = 5;
    
    /** Utility for generating OTPs. */
    private final AuthHelper authHelper;
    
    /** Kafka template for sending authentication-related events. */
    private final KafkaTemplate<String, WhatsappEvent> whatsappEventTemplate;
    
    /** Validation component for user-related operations. */
    private final UserValidation userValidation;
    
    /** Validation component for authentication-related operations. */
    private final AuthValidation authValidation;
    
    /** Redis template for storing OTPs with expiration. */
    private final RedisTemplate<String, String> redisTemplate;
    
    private final EmailService emailService;
    
    /** This method returns the type of authentication request that this service handles,
     * which is SEND_OTP. This information is used by the AuthenticationFactory to
     * route incoming requests to the appropriate service based on the request type
     * specified in the AuthenticationRequest object.
     *
     * @return The AuthenticationRequestType that this service handles, which is
     * SEND_OTP.
     */
    @Override
    public AuthenticationRequestType getAuthenticationRequestType() {
        return AuthenticationRequestType.SEND_OTP;
    }
    
    /** This method performs the operation of sending an OTP to the user. It validates
     * the phone number, checks for OTP request limits, generates an OTP, stores it
     * in Redis with an expiration time, and sends the OTP to the user via a Kafka
     * event. If any validation fails or an unexpected error occurs, it logs the
     * error and rethrows the exception.
     *
     * @param request The AuthenticationRequest containing the phone number for which
     *                the OTP should be sent.
     * @return An AuthenticationResponse indicating the result of the operation.
     */
    @Override
    public AuthenticationResponse performOperation(
            AuthenticationRequest request) {
        
        if (request.getPhoneNumber() != null) {
            
            return handlePhoneNumberSendOtp(request);
            
        } else if (request.getEmail() != null) {
            
            return handleEmailSendOtp(request);
        
        } else {
            log.warn("Invalid authentication request: both phone number and email are missing.");
            throw new InvalidAuthenticationRequestException(
                    "Invalid authentication request: both phone number and email are missing.");
        }
    }
    
    /** This method handles the operation of sending an OTP to the user's phone number.
     * It validates the phone number, checks for OTP request limits, generates an OTP,
     * stores it in Redis with an expiration time, and sends the OTP to the user via
     * a Kafka event. If any validation fails or an unexpected error occurs, it logs
     * the error and rethrows the exception.
     *
     * @param request The AuthenticationRequest containing the phone number for which
     *                the OTP should be sent.
     * @return An AuthenticationResponse indicating the result of the operation.
     */
    private AuthenticationResponse handlePhoneNumberSendOtp(
            AuthenticationRequest request){
        try{
            
            log.info("Processing SEND_OTP request for phone number: {}",
                    request.getPhoneNumber());
            
            String phoneNumber = userValidation
                    .validateAndNormalizePhoneNumber(request.getPhoneNumber());
            log.info("Validated and normalized phone number: {}",
                    phoneNumber);
            
            authValidation.validateOtpRequestLimit(
                    phoneNumber,
                    OTP_LIMIT,
                    WINDOW_MINUTES,
                    OTP_RATE_KEY
            );
            log.info("OTP limit validation passed Successfully.");
            
            String otp = authHelper.otpGenerate();
            log.info("Generated OTP: {} for phone number: {}",
                    otp, phoneNumber);
            
            redisTemplate.opsForValue().set(
                    getOtpRedisKey(phoneNumber),
                    otp,
                    Duration.ofMinutes(EXPIRY_TIME)
            );
            
            whatsappEventTemplate.send(OTP_TOPIC,
                    new WhatsappEvent(phoneNumber, otp));
            
            return AuthenticationResponse.builder()
                    .message("OTP sent successfully!")
                    .build();
            
        }catch (InvalidAuthenticationRequestException e){
            
            log.warn(
                    "Invalid authentication request for phone number: {}. Error: {}",
                    request.getPhoneNumber(), e.getMessage());
            throw e;
            
        }catch (Exception e){
            
            log.error("Unexpected error while sending OTP to {}",
                    request.getPhoneNumber(), e);
            throw e;
        }
    }
    
    /** This method handles the operation of sending an OTP to the user's email address.
     * It validates the email, checks for OTP request limits, generates an OTP, stores
     * it in Redis with an expiration time, and sends the OTP to the user via email.
     * If any validation fails or an unexpected error occurs, it logs the error and
     * rethrows the exception.
     *
     * @param request The AuthenticationRequest containing the email address for which
     *                the OTP should be sent.
     * @return An AuthenticationResponse indicating the result of the operation.
     */
    private AuthenticationResponse handleEmailSendOtp(
            AuthenticationRequest request){
        
        try{
            log.info("Processing SEND_OTP request for email: {}",
                    request.getEmail());
            
            String email = userValidation.validateAndNormalizeEmail(
                    request.getEmail());
            
            log.info("Validated and normalized email: {}",
                    email);
            
            authValidation.validateOtpRequestLimit(
                    email,
                    OTP_LIMIT,
                    WINDOW_MINUTES,
                    OTP_RATE_KEY
            );
            log.info("OTP limit validation passed Successfully.");
            
            String otp = authHelper.otpGenerate();
            
            log.info("Generated OTP: {} for email: {}",
                    otp, email);
            
             redisTemplate.opsForValue().set(
                    getOtpRedisKey(email),
                    otp,
                    Duration.ofMinutes(EXPIRY_TIME)
            );
             
             emailService.mailSender(email, "School Management System",
                     "Your OTP Code Please use the following OTP to "
                             +"complete your authentication: " + otp);
             
            return AuthenticationResponse
                    .builder()
                    .message("OTP sent successfully")
                    .build();
            
        }catch (InvalidAuthenticationRequestException e){
            log.warn(
                    "Invalid authentication request for email: {}. Error: {}",
                    request.getEmail(), e.getMessage());
            throw e;
        }catch (Exception e){
            log.error("Unexpected error while sending OTP to {}",
                    request.getEmail(), e);
            throw e;
        }
        
    }
    
    /** Helper method to construct the Redis key for storing the OTP associated with a
     * given phone number. This method concatenates a predefined prefix with the
     * normalized phone number to create a unique key for each user's OTP in Redis.
     *
     * @param identifier The normalized phone number for which the OTP is being stored.
     * @return A String representing the Redis key for the OTP associated with the
     * given phone number.
     */
    private String getOtpRedisKey(final String identifier){
        return OTP_KEY + identifier;
    }
}
