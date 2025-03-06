package com.beyond3.yyGang.nutrientAnswer.controller;

import com.beyond3.yyGang.answer.service.AnswerService;
import com.beyond3.yyGang.nutrientAnswer.NAnswer;
import com.beyond3.yyGang.nutrientAnswer.dto.NurtientAnswerRequestDto;
import com.beyond3.yyGang.nutrientAnswer.dto.NutrientAnswerResponseDto;
import com.beyond3.yyGang.nutrientAnswer.service.NurtientAnswerService;
import com.beyond3.yyGang.nutrientQuestion.dto.NutrientQuestionRequestDto;
import com.beyond3.yyGang.nutrientQuestion.service.NutrientQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NutrientQuestion")
@RequiredArgsConstructor
@Tag(name = "NAnswer", description = "상품 문의에 대한 답글")
public class NutrientAnswerController {
    private final NurtientAnswerService nurtientAnswerService;

    @PostMapping("/{NqboardId}/answer")
    @Operation(summary = "문의 답변 등록", description = "문의의 답변을 등록한다.")
    public ResponseEntity<NAnswer> saveNAnswer(@PathVariable Long NqboardId, @RequestBody NurtientAnswerRequestDto requestDto) {
        nurtientAnswerService.saveAnswer(NqboardId,requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{NqboardId}/answer")
    @Operation(summary = "답변 조회", description = "답글을 조회합니다.")
    public ResponseEntity<List<NutrientAnswerResponseDto>> getNAnswer(@PathVariable Long NqboardId) {


        return ResponseEntity.ok();
    }

}
