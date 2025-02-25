package com.adarsh.RealQuizzApp.repo;

import com.adarsh.RealQuizzApp.modal.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}

