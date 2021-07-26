package com.example.trip_note_api.controller.Auth;

import com.example.trip_note_api.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    AuthService authService;

    @PostMapping
    public ResponseEntity<Object> signup(@Validated @RequestBody SigninForm signinForm, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("エラー処理いれる");
        }
        System.out.println("------------------------------------");
        System.out.println("signup post");
        System.out.println("------------------------------------");
        authService.signup(signinForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        return ResponseEntity.status(HttpStatus.OK).body("signup post");
    }

    @GetMapping
    public ResponseEntity<Object> testSignup() {
        System.out.println("---------------------------------");
        System.out.println("testSignup");
        System.out.println("---------------------------------");
        return ResponseEntity.status(HttpStatus.OK).body("hello world");
    }
}