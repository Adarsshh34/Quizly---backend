package com.adarsh.RealQuizzApp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://quizly-by-adarsh.netlify.app")
public class HelloController {

    @GetMapping("/hello")
    public String greet(){
        return "Hello world";
    }
}
