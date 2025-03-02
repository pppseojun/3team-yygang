package com.beyond3.yyGang.answer.controller;


import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.dto.AnswerRequestDto;
import com.beyond3.yyGang.answer.dto.AnswerResponseDto;
import com.beyond3.yyGang.answer.service.AnswerService;
import com.beyond3.yyGang.user.dto.UserInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
@Tag(name = "Answer", description = "약 질문에 대한 답글")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/board/{id}/answers")
    @Operation(summary = "답글 등록", description = "답글을 등록한다")
    public ResponseEntity<Answer> saveAnswer(@PathVariable Long id, @RequestBody AnswerRequestDto answerDto) {

        answerService.saveAnswer(id,answerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}/answers")
    @Operation(summary = "답글 조회", description = "답글ID로 답글을 조회 한다.")
    public ResponseEntity<List<AnswerResponseDto>> getAnswer(@PathVariable Long id) {

        List<AnswerResponseDto> answerList = answerService.getAnswerById(id);

        return ResponseEntity.ok(answerList);
    }

    @GetMapping("/board/{qboardId}/answers")
    @Operation(summary = "답글 조회", description = "게시글ID로 답글을 조회 한다.")
    public ResponseEntity<List<AnswerResponseDto>> getAnswerByBoardId(@PathVariable Long qboardId) {

        List<AnswerResponseDto> answerList = answerService.getAnswerByBoardId(qboardId);

        return ResponseEntity.ok(answerList);
    }

    @PutMapping("/board/{qboardId}/answer/{answerId}")
    @Operation(summary = "답글 수정", description = " 답글을 수정 합니다.")
    public ResponseEntity<Object> updateAnswer(@PathVariable Long qboardId, @PathVariable Long answerId,@RequestBody AnswerRequestDto requestDto) {

        answerService.updateAnswer(qboardId,answerId,requestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/board/{qboardId}/answers/{answerId}")
    @Operation(summary = "답글 삭제", description = "답글을 삭제 합니다.")
    public ResponseEntity<Object> deleteAnswer(@PathVariable Long qboardId, @PathVariable Long answerId) {

        answerService.deleteAnswer(qboardId,answerId);

        return ResponseEntity.ok().build();
    }



    

}
