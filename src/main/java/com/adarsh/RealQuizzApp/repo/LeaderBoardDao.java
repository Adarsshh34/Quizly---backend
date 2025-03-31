package com.adarsh.RealQuizzApp.repo;

import com.adarsh.RealQuizzApp.modal.LeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderBoardDao extends JpaRepository<LeaderBoard,Integer> {

}
