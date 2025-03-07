package com.beyond3.yyGang.nutrientAnswer.dto;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.nutrientAnswer.NAnswer;
import com.beyond3.yyGang.nutrientQuestion.NutrientQuestion;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NurtientAnswerRequestDto {

    private Long questionId;
    private Long userId;
    private String answerContent;

    public NAnswer toEntity(User user) {
        return NAnswer.builder()
                .aContent(answerContent)
                .user(user)
                .build();
    }

}
