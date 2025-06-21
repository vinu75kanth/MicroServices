package com.telusko.Question.Controller;


import com.telusko.Question.Model.Question;
import com.telusko.Question.Model.QuestionWrapper;
import com.telusko.Question.Model.Response;
import com.telusko.Question.Service.QuestionService;
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

    @GetMapping("/generateQuestions")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int nq){
        return questionService.generateQuestions(category, nq);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds){
        return questionService.getQuestions(questionIds);
    }

    @PostMapping("/calculate")
    public ResponseEntity<Integer> calculate(@RequestBody List<Response> responses){
        return questionService.calculate(responses);
    }
    //generate
    //getQuestions
    //calculate

}
