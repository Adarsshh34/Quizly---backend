package com.adarsh.RealQuizzApp.service;

import com.adarsh.RealQuizzApp.modal.Question;
import com.adarsh.RealQuizzApp.modal.QuestionWrapper;
import com.adarsh.RealQuizzApp.modal.Quiz;
import com.adarsh.RealQuizzApp.modal.Response;
import com.adarsh.RealQuizzApp.repo.QuestionDao;
import com.adarsh.RealQuizzApp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizrepo;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<Integer> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsbByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        // Save Quiz and get the generated ID
        Quiz savedQuiz = quizrepo.save(quiz);
        Integer latestQuizId = savedQuiz.getId();  // Fetch latest quiz ID

        return new ResponseEntity<>(latestQuizId, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(int id) {

        Optional<Quiz> quiz = quizrepo.findById(id);

        List<Question> questionsFormDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q :questionsFormDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }


        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizrepo.findById(id).get();
        List<Question> questions  = quiz.getQuestions();
        int right = 0;
        int i=0;
        for( Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }

        return new ResponseEntity<>(right,HttpStatus.OK);

    }
}
