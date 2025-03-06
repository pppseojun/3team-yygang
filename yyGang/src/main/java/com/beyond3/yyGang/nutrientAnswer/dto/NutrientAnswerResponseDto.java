package com.beyond3.yyGang.nutrientAnswer.dto;

import com.beyond3.yyGang.nutrientAnswer.NAnswer;
import com.beyond3.yyGang.nutrientQuestion.NutrientQuestion;
import com.beyond3.yyGang.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NutrientAnswerResponseDto {

    private final Long answerId;
    private final Long questionId;
    private final Long userId;
    private final String answerContent;
    private final LocalDateTime crateAt;

    @Builder
    public NutrientAnswerResponseDto(NAnswer nAnswer) {
        this.answerId = nAnswer.getAnswerId();
        this.questionId = (nAnswer.getUser() != null) ? nAnswer.getUser().getUserId() : null;
        this.userId = (nAnswer.getUser() != null) ? nAnswer.getUser().getUserId() : null;
        this.crateAt = nAnswer.getCreatedAt();
        this.answerContent = nAnswer.getAContent();
    }

}
