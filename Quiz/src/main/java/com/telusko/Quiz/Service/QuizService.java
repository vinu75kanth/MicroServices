package com.telusko.Quiz.Service;

import com.telusko.Quiz.Feign.QuestionService;
import com.telusko.Quiz.Model.QuestionWrapper;
import com.telusko.Quiz.Model.Quiz;
import com.telusko.Quiz.Model.Response;
import com.telusko.Quiz.Repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    private QuizRepo quizRepo;
    private QuestionService questionService;

    @Autowired
    public void setQuizRepo(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }


    public ResponseEntity<String> createQuiz(String category, int nq, String title) {
        List<Integer> questionIds = questionService.generateQuestions(category,nq).getBody();

        Quiz quiz = new Quiz();
        quiz.setQuizName(title);
        quiz.setQuestionIds(questionIds);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Quiz Created Successfully",HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {

        Quiz quiz = quizRepo.getById(id);
        List<Integer> questionIds = quiz.getQuestionIds();

        return questionService.getQuestions(questionIds);

    }

    public ResponseEntity<Integer> evaluate(int id, List<Response> responses) {

        return questionService.calculate(responses);
    }
}
