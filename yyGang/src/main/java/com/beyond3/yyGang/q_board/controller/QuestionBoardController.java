package com.beyond3.yyGang.q_board.controller;

import com.beyond3.yyGang.answer.dto.AnswerResponseDto;
import com.beyond3.yyGang.q_board.service.QuestionBoardService;
import com.beyond3.yyGang.q_board.dto.QboardRequestDto;
import com.beyond3.yyGang.q_board.dto.QboardResponseDto;
import com.beyond3.yyGang.q_board.dto.QboardUpdateDto;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller
@RequestMapping("/qboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@Tag(name="Qboard", description = "약 질문 게시판")
public class QuestionBoardController {

    private final QuestionBoardService questionBoardService;

    //게시글 작성
    @Operation(summary = "게시글 등록", description = "게시글을 등록한다.")
    @PostMapping
    public ResponseEntity<QuestionBoard> saveQuestionBoard(@RequestBody QboardRequestDto requestDto) {
        questionBoardService.saveQboard(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시글 조회
    @Operation(summary = "전제 게시글 조회", description = "전체 게시글을 조회한다.")
    @GetMapping
    public ResponseEntity<List<QboardResponseDto>> getAllQuestionBoard(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue="10") int size) {
        Page<QboardResponseDto> qboardList = questionBoardService.getAllQboard(page,size);
        return ResponseEntity.ok(qboardList.getContent());
    }

    // 특정 ID로 게시글 조회
    @GetMapping("/{qboardId}")
    @Operation(summary = "특정 ID 게시글 조회", description = "특정 ID로 게시글을 조회한다.")
    public ResponseEntity<QboardResponseDto> getQuestionBoardById(@PathVariable Long qboardId) {

        QboardResponseDto questionBoard = questionBoardService.getQboardById(qboardId);

        return ResponseEntity.ok(questionBoard);
    }


    // 특정 페이지 게시글 수정
    @PutMapping("/{qboardId}")
    @Operation(summary = "게시글 수정", description = "게시글 수정 한다")
    public ResponseEntity<Object> update(@RequestBody QboardUpdateDto requestDto, @PathVariable Long qboardId) {

        questionBoardService.updateQboard(qboardId,requestDto);

        return ResponseEntity.noContent().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{qboardId}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제 합니다.")
    public ResponseEntity<String> delete(@PathVariable Long qboardId) {
        questionBoardService.deleteQboard(qboardId);
        return  ResponseEntity.ok("성공적으로 삭제 되었습니다.");
    }

}
