package com.quiz.quizapp.Service;

import com.quiz.quizapp.Model.Question;
import com.quiz.quizapp.Model.QuestionWrapper;
import com.quiz.quizapp.Model.Quiz;
import com.quiz.quizapp.Model.Response;
import com.quiz.quizapp.Repo.QuestionRepo;
import com.quiz.quizapp.Repo.QuizRepo;
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
    private QuestionRepo questionRepo;

    @Autowired
    public void setQuizRepo(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }

    @Autowired
    public void setQuestionRepo(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public ResponseEntity<String> createQuiz(String category, int nq, String title) {
        try {
            List<Question> list = questionRepo.createQuiz(category, nq);
            Quiz quiz = new Quiz();
            quiz.setQuizName(title);
            quiz.setQuestions(list);
            quizRepo.save(quiz);
            return new ResponseEntity("Quiz created", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        Quiz quiz = quizRepo.getById(id);
        List<Question> questions = quiz.getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(), question.getQuestion(),question.getOp1(),
                                                                    question.getOp2(), question.getOp3(), question.getOp4());
//            System.out.println(questionWrapper);
            questionWrappers.add(questionWrapper);  
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> evaluate(int id, List<Response> responses) {
//        System.out.println(responses.size());
        List<Question> questions = quizRepo.getById(id).getQuestions();
        Map<Integer,Integer> map = new HashMap<>();
        for(Question question : questions) {
            map.put(question.getId(),question.getAns());
//            System.out.println(question.getId() + " " + question.getAns());
        }

        int ans = 0;

        for(Response response : responses) {
            if(!map.containsKey(response.getId())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(map.get(response.getId()) == response.getAns()) {
                ans++;
            }
        }
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }
}
