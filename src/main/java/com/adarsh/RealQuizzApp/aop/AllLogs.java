package com.adarsh.RealQuizzApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class AllLogs {

    private static final Logger alllogs = LoggerFactory.getLogger("allLogs");

    @Before(
            "execution(* com.adarsh.RealQuizzApp.service.QuizService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.controller.BmiycController.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.controller.LeaderBoardController.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.controller.QuestionController.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.controller.QuizzController.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.controller.UserController.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.BmiycService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.QuestionService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.LeaderBoardService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.QuizService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.UserService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.repo.BmiycRepo.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.repo.QuestionDao.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.repo.UserRepo.*(..))"
    )
    public void allRequest (JoinPoint joinPoint) throws Throwable{

        String methodName = joinPoint.getSignature().toShortString();
        alllogs.info("Method called: {}", methodName);


    }
}
