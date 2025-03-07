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
import java.util.Optional;

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

    // 상품의 대한 문의는 단건으로 조회
    @GetMapping("/{NqboardId}/answer")
    @Operation(summary = "답변 조회", description = "답글을 조회합니다.")
    public ResponseEntity<NutrientAnswerResponseDto> getNAnswer(@PathVariable Long NqboardId) {

        return ResponseEntity.ok(nurtientAnswerService.getAnswerById(NqboardId));
    }

    @PutMapping("/{NqboardId}/answer")
    @Operation(summary = "답변 수정", description = "답변을 수정 합니다,")
    public ResponseEntity<Object> updateNAnswer(@PathVariable Long NqboardId, @RequestBody NurtientAnswerRequestDto requestDto) {
        nurtientAnswerService.updateAnswer(NqboardId,requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{NqboardId}/answer")
    @Operation(summary = "답변 삭제", description = "답변을 삭제 합니다.")
    public ResponseEntity<Object> deleteNAnswer(@PathVariable Long NqboardId) {

        nurtientAnswerService.deleteAnswer(NqboardId);
        return ResponseEntity.ok().build();
    }

}
