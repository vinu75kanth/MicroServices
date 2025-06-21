package com.quiz.quizapp.Repo;

import com.quiz.quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findQuestionByCategory(String category);

    @Query(value = "select * from Question q where q.category = :category order by random() limit :nq",nativeQuery = true)
    List<Question> createQuiz(String category, int nq);
}
