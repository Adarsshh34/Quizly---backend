package com.adarsh.RealQuizzApp.repo;


import com.adarsh.RealQuizzApp.modal.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {



    List<Question> findByCategory(String catName);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ"
            ,nativeQuery = true)
    List<Question> findRandomQuestionsbByCategory(String category, int numQ);
}

