package com.adarsh.RealQuizzApp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceLogging {

    private static final Logger logger = LoggerFactory.getLogger("performance");

    @Around(
            "execution(* com.adarsh.RealQuizzApp.service.QuizService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.LeaderBoardService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.QuestionService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.QuizService.*(..))" +
                    " || execution(* com.adarsh.RealQuizzApp.service.UserService.*(..))"
            )
    public Object allRequest (ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // Execute the method

        long end = System.currentTimeMillis();
//        System.out.println(joinPoint.getSignature() + " executed in " + (end - start) + "ms" );
        logger.info(joinPoint.getSignature() + " executed in " + (end - start) + "ms" );  // Log to file
        return result;

    }
}
