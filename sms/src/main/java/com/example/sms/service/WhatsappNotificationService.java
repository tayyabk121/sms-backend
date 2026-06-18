package com.example.sms.service;

/** Service interface for sending WhatsApp notifications. This interface
 * provides methods to send OTP (One-Time Password) messages to users via
 * WhatsApp. It can be extended to include additional notification
 * functionalities as needed. */
public interface WhatsappNotificationService {
    
    /** Sends an OTP message to the specified phone number via WhatsApp.
     *
     * @param phoneNumber The recipient's phone number in E.164 format
     *(without the "whatsapp:" prefix).
     * @param otp The One-Time Password (OTP) to be sent to the recipient.
     */
    void sentOtp (String phoneNumber, String otp);
}
