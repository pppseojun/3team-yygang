package com.beyond3.yyGang.nutrientQuestion.dto;


import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nutrientQuestion.NutrientQuestion;
import com.beyond3.yyGang.user.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutrientQuestionRequestDto {

    private Long supplementId;
    private Long userId;
    private String qContent;

    public NutrientQuestion toEntity(NSupplement supplement, User user ) {
        return NutrientQuestion.builder()
                .qContent(this.qContent)
                .supplement(supplement)
                .user(user)
                .build();
    }
}
