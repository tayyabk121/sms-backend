package com.example.sms.kafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/** Represents an event related to WhatsApp communication, containing the
 * recipient's phone number and the OTP (One-Time Password) to be sent. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WhatsappEvent {
    /** The recipient's phone number in E.164 format (
     * without the "whatsapp:" prefix). */
    private String phoneNumber;
    /** The One-Time Password (OTP) to be sent to the recipient. */
    private String otp;
}
