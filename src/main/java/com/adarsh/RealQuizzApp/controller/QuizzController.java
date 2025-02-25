package com.adarsh.RealQuizzApp.controller;


import com.adarsh.RealQuizzApp.modal.Question;
import com.adarsh.RealQuizzApp.modal.QuestionWrapper;
import com.adarsh.RealQuizzApp.modal.Response;
import com.adarsh.RealQuizzApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@CrossOrigin(origins = "http://localhost:5173")
public class QuizzController {

    @Autowired
    QuizService service;


    //    localhost:8080/quiz/create?category=Java&numQ=5&title=JQuiz
    @PostMapping("create")
    public ResponseEntity<Integer> createQuizz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title){
        return service.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable int id){
        return service.getQuizQuestion(id);

    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response ){
        return service.calculateResult(id,response);
    }



}
