package com.beyond3.yyGang.answer.controller;


import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.dto.AnswerRequestDto;
import com.beyond3.yyGang.answer.service.AnswerService;
import com.beyond3.yyGang.user.dto.UserInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
@Tag(name = "Answer", description = "얄 질문에 대한 답글")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/board/{id}/answers")
    @Operation(summary = "답글", description = "답글 조회")
    public ResponseEntity<Answer> saveAnswer(@PathVariable Long id, @RequestBody AnswerRequestDto answerDto) {

        return ResponseEntity.ok(answerService.commentSave(id,answerDto));
    }

}
