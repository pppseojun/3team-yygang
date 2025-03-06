package com.beyond3.yyGang.nutrientQuestion;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping
    @Operation(summary = "상품 문의 전체 조회", description = "상문 문의 전체 목록을 조회 합니다.")
    public ResponseEntity<List<NutrientQuestionResponseDto>> getAllNutrientQuestion(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        Page<NutrientQuestionResponseDto> nqboard = nutrientQuestionService.getAllNqboard(page, size);

        return ResponseEntity.ok(nqboard.getContent());
    }

    @GetMapping("/{nqboardId}")
    @Operation(summary = "특정 ID로 상품문의 조회", description = "특정 ID로 살품문의를 조회한다.")
    public ResponseEntity<NutrientQuestionResponseDto> getNutrientQuestionById(@PathVariable Long nqboardId) {
        NutrientQuestionResponseDto nutrientQuestion = nutrientQuestionService.getNqboardById(nqboardId);
        return ResponseEntity.ok(nutrientQuestion);
    }
}
