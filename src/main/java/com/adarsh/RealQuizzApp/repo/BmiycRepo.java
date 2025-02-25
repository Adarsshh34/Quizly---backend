package com.adarsh.RealQuizzApp.repo;

import com.adarsh.RealQuizzApp.modal.Beatmeifyoucan;
import com.adarsh.RealQuizzApp.modal.QuestionWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BmiycRepo extends JpaRepository<Beatmeifyoucan,Integer> {

    @Query(value = "(SELECT * FROM Beat_me_if_you_can q WHERE q.difficultylevel = 'easy' " +
            "ORDER BY q.usage_count ASC  LIMIT :numQ) " +
            "UNION ALL " +
            "(SELECT * FROM Beat_me_if_you_can q WHERE q.difficultylevel = 'medium' " +
            "ORDER BY q.usage_count ASC  LIMIT :numQ) " +
            "UNION ALL " +
            "(SELECT * FROM Beat_me_if_you_can q WHERE q.difficultylevel = 'hard' " +
            "ORDER BY q.usage_count ASC  LIMIT :numQ) " +
            "UNION ALL " +
            "(SELECT * FROM Beat_me_if_you_can q WHERE q.difficultylevel = 'tricky' " +
            "ORDER BY q.usage_count ASC  LIMIT :numQ) ",
            nativeQuery = true)
    List<Beatmeifyoucan> findQuestionByDifficulty(int numQ);
//    db will give question that has been least used in the quiz
//    frontend has to choose randomly 7 question out of whose question.

    @Modifying
    @Transactional
    @Query(value = "UPDATE beat_me_if_you_can  SET usage_count = usage_count + 1 WHERE id IN :ids",nativeQuery = true)
    int updateUsageCount(@Param("ids") List<Integer> ids);
}
