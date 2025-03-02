package com.beyond3.yyGang.answer.dto;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import jakarta.xml.ws.BindingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class AnswerResponseDto {
    private Long answerId;
    private String answerContent;
    private LocalDateTime answerDate;
    private LocalDateTime answerMdate;
    private Long userId;
    private Long questionId;

    @Builder
    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.answerContent = answer.getAnswerContent();
        this.answerDate = answer.getAnswerDate();
        this.answerMdate = answer.getAnswerMdate();
        this.userId = (answer.getUser() != null) ? answer.getUser().getUserId() : null;
        this.questionId = (answer.getQboard()!= null) ? answer.getQboard().getQboardId() : null;
    }
}
