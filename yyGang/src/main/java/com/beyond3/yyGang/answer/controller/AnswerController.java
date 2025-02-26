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
    // board id 하고 userid는 PathVariable 로 받아서 해보는 방향
    public ResponseEntity<Answer> saveAnswer(@PathVariable Long id, @RequestBody AnswerRequestDto answerDto) {

        return ResponseEntity.ok(answerService.saveAnswer(id,answerDto));
    }

    @GetMapping("/board/{id}/answers")
    @Operation(summary = "답글 조회", description = "답글을 조회 한다.")
    public ResponseEntity<List<Answer>> getAnswer(@PathVariable Long id) {



        return ResponseEntity.ok(answerService.getAnswerByQuestion(id))
    }

}
