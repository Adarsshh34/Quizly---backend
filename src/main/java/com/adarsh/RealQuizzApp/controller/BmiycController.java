package com.adarsh.RealQuizzApp.controller;

import com.adarsh.RealQuizzApp.modal.Beatmeifyoucan;
import com.adarsh.RealQuizzApp.modal.QuestionWrapper;
import com.adarsh.RealQuizzApp.modal.Response;
import com.adarsh.RealQuizzApp.service.BmiycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beatmeifyoucan")
@CrossOrigin(origins = "http://localhost:5173")
public class BmiycController {

    @Autowired
    BmiycService service;

    @GetMapping("get/{numQ}")
    public ResponseEntity<List<Beatmeifyoucan>> getEasyQues(@PathVariable int numQ){
        return service.GetQuestions(numQ);

    }

    @PostMapping("updatecount")
    public ResponseEntity<String> getEasyQues(@RequestBody List<Integer> usedIds){
        return service.UpdateCount(usedIds);

    }





}
