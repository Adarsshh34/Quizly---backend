package com.adarsh.RealQuizzApp.service;

import com.adarsh.RealQuizzApp.modal.Beatmeifyoucan;
import com.adarsh.RealQuizzApp.modal.QuestionWrapper;
import com.adarsh.RealQuizzApp.repo.BmiycRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BmiycService {

    @Autowired
    BmiycRepo repo;


    public ResponseEntity<List<Beatmeifyoucan>> GetQuestions(int numQ) {
//        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        List<Beatmeifyoucan> quest = (List<Beatmeifyoucan>) repo.findQuestionByDifficulty(numQ);

        if(quest!=null){
            return new ResponseEntity<>(quest,HttpStatus.OK);
        }
        return new ResponseEntity<>(quest,HttpStatus.NOT_FOUND);


    }

    public ResponseEntity<String> UpdateCount(List<Integer> usedIds) {

        int updatedRows = repo.updateUsageCount(usedIds);

        if (updatedRows > 0) {
            return new ResponseEntity<>("Successfully updated " + updatedRows + " records", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records updated. IDs might be invalid.", HttpStatus.NOT_FOUND);
        }

//        repo.updateUsageCount(usedIds);
//
////        update this
//        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
