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

    private String answerContent;
    private Long qboardId;
    private Long userId;

    public Answer toEntity( QuestionBoard qBoard, User user) {
        return Answer.builder()
                .answerContent(answerContent)
                .user(user)
                .qboard(qBoard)
                .build();
    }

}
