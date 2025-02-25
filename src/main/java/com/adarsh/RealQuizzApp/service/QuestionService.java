package com.adarsh.RealQuizzApp.service;


import com.adarsh.RealQuizzApp.modal.Question;
import com.adarsh.RealQuizzApp.repo.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao repo;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try {

            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<Question>> getAllQuesByCat(String catName) {

        try {
            return new ResponseEntity<>(repo.findByCategory(catName),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String>  addQuestion(Question question) {

        repo.save(question);
        return new ResponseEntity<>( "Question Added Succesfully",HttpStatus.OK);
    }
}

