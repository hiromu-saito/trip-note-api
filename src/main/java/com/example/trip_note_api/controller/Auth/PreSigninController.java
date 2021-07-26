package com.example.trip_note_api.controller.Auth;

import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/preSignin")
public class PreSigninController {

    @GetMapping
    public String preSignin(HttpServletRequest request){
        System.out.println("---------------------");
        DefaultCsrfToken token = (DefaultCsrfToken) request.getAttribute("_csrf");
        if(token == null){
            throw new RuntimeException("could not get a token");
        }
        return token.getToken();
    }
}
