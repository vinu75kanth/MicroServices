package com.telusko.Quiz.Controller;

import com.telusko.Quiz.Model.QuestionWrapper;
import com.telusko.Quiz.Model.Response;
import com.telusko.Quiz.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int nq, @RequestParam String title) {
        return quizService.createQuiz(category,nq,title);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable int id){
        return quizService.getQuizById(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> evaluate(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.evaluate(id,responses);
    }

}
