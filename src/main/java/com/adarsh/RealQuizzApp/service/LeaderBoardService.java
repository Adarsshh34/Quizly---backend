package com.adarsh.RealQuizzApp.service;

import com.adarsh.RealQuizzApp.modal.LeaderBoard;
import com.adarsh.RealQuizzApp.repo.LeaderBoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderBoardService {

    @Autowired
    LeaderBoardDao repo;

    public ResponseEntity<String> submitScore(LeaderBoard score) {

        repo.save(score);
        return new ResponseEntity<>("Score Added", HttpStatus.OK);
    }

    public ResponseEntity<List<LeaderBoard>> getData() {
        return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
    }
}
