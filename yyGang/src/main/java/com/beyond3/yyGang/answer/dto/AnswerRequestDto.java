package com.beyond3.yyGang.answer.dto;


import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRequestDto {


    private Long answerId;
    private String answerContent;
    private LocalDateTime answerDate;
    private LocalDateTime answerMdate;
    private User user;
    private QuestionBoard qboard;

    public Answer toEntity(){
        return Answer.builder()
                .answerId(answerId)
                .answerContent(answerContent)
                .answerDate(answerDate)
                .answerMdate(answerMdate)
                .user(user)
                .qboard(qboard)
                .build();
    }

}
