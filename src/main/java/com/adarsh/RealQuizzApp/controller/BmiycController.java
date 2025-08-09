package com.adarsh.RealQuizzApp.controller;

import com.adarsh.RealQuizzApp.modal.BMIResponse;
import com.adarsh.RealQuizzApp.modal.Beatmeifyoucan;
import com.adarsh.RealQuizzApp.service.BmiycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beatmeifyoucan")
@CrossOrigin(origins = "http://quizly-by-adarsh.netlify.app")
public class BmiycController {

    @Value("${frontend.url}")
    private String Frontend_url;

    @Autowired
    BmiycService service;

    @GetMapping("get/{numQ}")
    public ResponseEntity<List<Beatmeifyoucan>> getEasyQues(@PathVariable int numQ){
        return service.GetQuestions(numQ);

    }

    @PostMapping("updatecount")
    public ResponseEntity<String> getEasyQues( @RequestBody List<BMIResponse> updateRes ){
        System.out.println("updateRes:"+updateRes);
        return service.UpdateCount(updateRes);


    }





}
