package com.quiz.quizapp.Repo;

import com.quiz.quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

}
