package com.telusko.Question.Repo;

import com.telusko.Question.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    List<Question> findQuestionByCategory(String category);

    @Query(value = "select q.id from Question q where q.category = :category order by random() limit :nq",nativeQuery = true)
    List<Integer> createQuiz(String category, int nq);
}
