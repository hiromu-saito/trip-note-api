package com.example.trip_note_api.controller;

import lombok.val;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class TestController {
    @GetMapping(value = "/")
    public String demo() {

        val fa = "aaa";
        return "hello world";
    }
}
