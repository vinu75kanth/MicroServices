package com.quiz.quizapp.Controller;

import com.quiz.quizapp.Model.Question;
import com.quiz.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/greet")
    public ResponseEntity<String> greet(){
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> allQuestions(){
        return questionService.allQuestions();
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("/findQuestionByCat")
    public ResponseEntity<List<Question>> findQuestionsByCategory(@RequestParam String category){
        return questionService.findQuestionByCategory(category);
    }

}
