package com.example.sms.exception;

/** Custom exception class for handling mail sending failures in the school
 * management system. This exception is thrown when an attempt to send an email
 * fails due to issues such as network problems, incorrect email addresses, or
 * other errors related to the email sending process. */
public class MailSendFailedException extends RuntimeException{
    public MailSendFailedException(String message) {
        super(message);
    }
}
