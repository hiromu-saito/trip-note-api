package com.example.trip_note_api.controller.Auth;

import com.example.trip_note_api.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping
    public ResponseEntity<Object> signup(@Validated @RequestBody LoginForm loginForm, Errors errors) {
        if (errors.hasErrors()) {
            System.out.println("エラー処理いれる");
        }
        authService.signup(loginForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
