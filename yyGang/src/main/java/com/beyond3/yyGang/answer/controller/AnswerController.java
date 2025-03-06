package com.beyond3.yyGang.answer.controller;


import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.dto.AnswerRequestDto;
import com.beyond3.yyGang.answer.dto.AnswerResponseDto;
import com.beyond3.yyGang.answer.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qboard")
@RequiredArgsConstructor
@Tag(name = "Answer", description = "약 질문에 대한 답글")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{qboardId}/answers")
    @Operation(summary = "답글 등록", description = "답글을 등록한다")
    public ResponseEntity<Answer> saveAnswer(@PathVariable Long qboardId, @RequestBody AnswerRequestDto answerDto) {

        answerService.saveAnswer(qboardId,answerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{qboardId}/answers/{answerId}")
    @Operation(summary = "특정 답글 조회", description = "답글ID로 답글을 조회 한다.")
    public ResponseEntity<List<AnswerResponseDto>> getAnswer(@PathVariable Long qboardId, @PathVariable Long answerId) {

        List<AnswerResponseDto> answerList = answerService.getAnswerById(qboardId,answerId);

        return ResponseEntity.ok(answerList);
    }

    @GetMapping("/{qboardId}/answers")
    @Operation(summary = "게시글별 전체 답글 조회", description = "게시글ID로 답글을 조회 한다.")
    public ResponseEntity<List<AnswerResponseDto>> getAnswerByBoardId(@PathVariable Long qboardId) {

        List<AnswerResponseDto> answerList = answerService.getAnswerByBoardId(qboardId);

        return ResponseEntity.ok(answerList);
    }

    @PutMapping("/{qboardId}/answers/{answerId}")
    @Operation(summary = "답글 수정", description = " 답글을 수정 합니다.")
    public ResponseEntity<Object> updateAnswer(@PathVariable Long qboardId, @PathVariable Long answerId,@RequestBody AnswerRequestDto requestDto) {

        answerService.updateAnswer(qboardId,answerId,requestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{qboardId}/answers/{answerId}")
    @Operation(summary = "답글 삭제", description = "답글을 삭제 합니다.")
    public ResponseEntity<Object> deleteAnswer(@PathVariable Long qboardId, @PathVariable Long answerId) {

        answerService.deleteAnswer(qboardId,answerId);

        return ResponseEntity.ok().build();
    }

}
