package com.beyond3.yyGang.answer.controller;

import com.beyond3.yyGang.answer.repository.AnswerRepository;
import com.beyond3.yyGang.answer.service.AnswerLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
@Tag(name = "Answer-Like", description = "답글에 대한 좋아요 기능")
public class AnswerLikeController {

    private final AnswerLikeService answerLikeService;
    private final AnswerRepository answerRepository;

    @PostMapping("{answerId}/like")
    @Operation(summary = "좋아요 등록", description = "답글에 좋아요를 누릅니다.")
    ResponseEntity<Object> likeAnswer(@RequestParam Long userId, @PathVariable("answerId") Long answerId ) {
        answerLikeService.likeAnswer(answerId,userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{answerId}/like")
    @Operation(summary = "좋아요 취소", description = "좋아요를 취소합니다")
    public ResponseEntity<Object> unlikeAnswer(@RequestParam Long userId, @PathVariable("answerId") Long answerId ) {
        answerLikeService.unLikeAnswer(userId,answerId);
        return ResponseEntity.ok().build();
    }


}
