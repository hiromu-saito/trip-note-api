package com.example.trip_note_api.domain.exception;

public class AuthException extends RuntimeException {
    public  AuthException(String msg){
        super(msg);
    }
}
