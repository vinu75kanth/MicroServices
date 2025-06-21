package com.telusko.Quiz.Feign;

import com.telusko.Quiz.Model.QuestionWrapper;
import com.telusko.Quiz.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION")
public interface QuestionService {

    @GetMapping("question/generateQuestions")
    ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int nq);

    @PostMapping("question/getQuestions")
    ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> questionIds);

    @PostMapping("question/calculate")
    ResponseEntity<Integer> calculate(@RequestBody List<Response> responses);

}
