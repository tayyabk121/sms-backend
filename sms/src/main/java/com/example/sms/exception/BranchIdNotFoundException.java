package com.example.sms.exception;

public class BranchIdNotFoundException extends RuntimeException{
    public BranchIdNotFoundException(String message) {
        super(message);
    }
}
