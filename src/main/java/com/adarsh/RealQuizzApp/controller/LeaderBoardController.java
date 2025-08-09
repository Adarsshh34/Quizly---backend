package com.adarsh.RealQuizzApp.controller;

import com.adarsh.RealQuizzApp.modal.LeaderBoard;
import com.adarsh.RealQuizzApp.service.LeaderBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("leaderboard")
@CrossOrigin(origins = "http://quizly-by-adarsh.netlify.app")
public class LeaderBoardController {

    @Autowired
    LeaderBoardService service;

    @PostMapping("score")
    public ResponseEntity<String> submitScore(@RequestBody LeaderBoard score){
        return service.submitScore(score);
    }

    @GetMapping
    public ResponseEntity<List<LeaderBoard>> getLeaderBoard (){
        return service.getData();
    }
}
