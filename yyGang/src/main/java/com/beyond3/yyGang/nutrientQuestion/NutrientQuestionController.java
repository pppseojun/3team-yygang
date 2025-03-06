package com.beyond3.yyGang.nutrientQuestion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NutrientQuestion")
@RequiredArgsConstructor
@Tag(name = "NQuestion", description = "상품 문의")
public class NutrientQuestionController {

    private final NutrientQuestionService nutrientQuestionService;

    @Operation(summary = "상문 문의 등록하기", description = "상문 문의를 등록합니다.")
    @PostMapping
    public ResponseEntity<NutrientQuestion> saveNutrientQuestion(@RequestBody NutrientQuestionRequestDto requestDto) {

        nutrientQuestionService.saveNquestion(requestDto);

        return ResponseEntity.noContent().build();
    }
}
