package com.beyond3.yyGang.answer.dto;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;

import java.time.LocalDateTime;

public class AnswerResponseDto {
    private Long answerId;
    private String answerContent;
    private LocalDateTime answerDate;
    private LocalDateTime answerMdate;
    private String userName;
    private Long questionId;

    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.answerContent = answer.getAnswerContent();
        this.answerDate = answer.getAnswerDate();
        this.answerMdate = answer.getAnswerMdate();
        this.userName = answer.getUser().getName();
        this.questionId = answer.getQboard().getQboardId();
    }
}
