package com.beyond3.yyGang.q_board;

import com.beyond3.yyGang.q_board.dto.QboardRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/qboard")
@Tag(name="Qboard", description = "약 질문 게시판")
public class QuestionBoardController {

    private final QuestionBoardService questionBoardService;

    public QuestionBoardController(QuestionBoardService questionBoardService) {
        this.questionBoardService = questionBoardService;
    }

    //게시글 작성
    @Operation(summary = "등록", description = "게시글 등록")
    @PostMapping("/save")
    public ResponseEntity<Object> createQuestionBoard(@RequestBody QboardRequestDto dto) {
        Long saveId = questionBoardService.saveQboard(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    // 게시글 조회
    @Operation(summary = "조회", description = "게시글 조회")
    @GetMapping("/findAll")
    public ResponseEntity<Object> getAllQuestionBoard() {
        return questionBoardService.getAllQboard();
    }

    // 특정 ID로 게시글 조회
    @GetMapping("/{id}")
    @Operation(summary = "특정 ID 게시글 조회", description = "특정ID로 게시글을 조회한다.")
    public ResponseEntity<QuestionBoard> getQuestionBoardById(@PathVariable Long id) {
        QuestionBoard questionBoard = questionBoardService.getQboardById(id);
        if (questionBoard == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionBoard);
    }


}
