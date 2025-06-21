package com.telusko.Question.Service;

import com.telusko.Question.Model.Question;
import com.telusko.Question.Model.QuestionWrapper;
import com.telusko.Question.Model.Response;
import com.telusko.Question.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public ResponseEntity<List<Integer>> generateQuestions(String category, int nq) {
        try {
            List<Integer> questions = questionRepo.createQuiz(category, nq);
            return new ResponseEntity<>(questions,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestions(List<Integer> questionIds) {
        try {
            List<Question> questions = questionRepo.findAllById(questionIds);
            List<QuestionWrapper> questionWrappers = new ArrayList<>();
            for (Question question : questions) {
                questionWrappers.add(new QuestionWrapper(question.getId(), question.getQuestion(), question.getOp1(), question.getOp2(), question.getOp3(), question.getOp4()));
            }
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculate(List<Response> responses) {
        try {
            List<Question> questions = questionRepo.findAllById(new ArrayList<>(responses).stream().map(Response::getId).toList());
            Map<Integer, Integer> map = questions.stream().collect(java.util.stream.Collectors.toMap(Question::getId, Question::getAns));
            int score = 0;
            for (Response response : responses) {
                if(!map.containsKey(response.getId())){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (response.getAns() == map.get(response.getId())) {
                    score++;
                }
            }
            return new ResponseEntity<>(score, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
