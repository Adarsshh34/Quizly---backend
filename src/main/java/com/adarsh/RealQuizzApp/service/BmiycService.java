package com.adarsh.RealQuizzApp.service;

import com.adarsh.RealQuizzApp.modal.BMIResponse;
import com.adarsh.RealQuizzApp.modal.Beatmeifyoucan;
import com.adarsh.RealQuizzApp.repo.BmiycRepo;
import jakarta.transaction.Transactional;
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

    @Transactional
    public ResponseEntity<String> UpdateCount(List<BMIResponse> usedIds) {

        for (BMIResponse usage : usedIds) {
            repo.updateUsageCount(usage.getId(), usage.getUsageCount());
        }
        return new ResponseEntity<>("Usage Count Updated!",HttpStatus.OK);




    }
}
