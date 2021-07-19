package com.example.trip_note_api.controller.Auth;

import com.example.trip_note_api.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void signup (@Validated  @RequestBody LoginForm loginForm, Errors errors){
        if(errors.hasErrors()){
            System.out.println("エラー処理いれる");
        }

        //service呼び出し
        authService.signup(loginForm);
    }
}
