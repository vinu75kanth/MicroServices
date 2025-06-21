package com.quiz.quizapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionWrapper {
    private int id;
    private String question;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
}
