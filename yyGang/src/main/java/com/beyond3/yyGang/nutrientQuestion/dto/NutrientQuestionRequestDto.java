package com.beyond3.yyGang.nutrientQuestion.dto;


import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nutrientQuestion.NutrientQuestion;
import com.beyond3.yyGang.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutrientQuestionRequestDto {

    @NotBlank(message = "문의 내용은 필수 입력입니다.")
    private String qContent;

    public NutrientQuestion toEntity(NSupplement supplement, User user ) {
        return NutrientQuestion.builder()
                .qContent(qContent)
                .supplement(supplement)
                .user(user)
                .build();
    }
}
