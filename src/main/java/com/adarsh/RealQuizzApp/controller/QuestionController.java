package com.adarsh.RealQuizzApp.controller;


import com.adarsh.RealQuizzApp.modal.Question;
import com.adarsh.RealQuizzApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionController {

    @Autowired
    private QuestionService service;


    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return service.getAllQuestions();
    }

    @GetMapping("/category/{catName}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String catName){
        return service.getAllQuesByCat(catName);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return service.addQuestion(question);
    }

}
