package com.quiz.quizapp.Service;

import com.quiz.quizapp.Model.Question;
import com.quiz.quizapp.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionRepo questionRepo;

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public ResponseEntity<List<Question>> allQuestions() {
        return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionRepo.save(question);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepo.findQuestionByCategory(category), HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
