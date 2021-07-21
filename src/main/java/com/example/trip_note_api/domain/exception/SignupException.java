package com.example.trip_note_api.domain.exception;

public class SignupException extends RuntimeException{
    public SignupException (String msg){
        super(msg);
    }
}
