package com.beyond3.yyGang.nutrientQuestion;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NutrientQuestionResponseDto {
    private final Long questionId;
    private final Long userId;
    private final Long supplementId;
    private final LocalDateTime createAt;
    private final  String qContent;

    @Builder
    public NutrientQuestionResponseDto(NutrientQuestion nQuestion) {
        this.questionId = nQuestion.getQuestionId();
        this.userId = nQuestion.getUser().getUserId();
        this.supplementId = nQuestion.getSupplement().getProductId();
        this.createAt = nQuestion.getCreateAt();
        this.qContent = nQuestion.getQContent();
    }
}
