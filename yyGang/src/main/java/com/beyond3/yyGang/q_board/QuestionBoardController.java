package com.beyond3.yyGang.q_board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/qboard")
public class QuestionBoardController {
    private final QuestionBoardService questionBoardService;

    public QuestionBoardController(QuestionBoardService questionBoardService) {
        this.questionBoardService = questionBoardService;
    }

    //게시글 작성
    @PostMapping
    public QuestionBoard createQuestionBoard(@RequestBody QuestionBoard questionBoard) {
        return questionBoardService.saveQboard(questionBoard);
    }

    // 게시글 조회
    @GetMapping
    public List<QuestionBoard> getAllQuestionBoard() {
        return questionBoardService.getAllQboard();
    }

    // 특정 ID로 게시글 조회
    @GetMapping("/{id}")
    public Optional<QuestionBoard> getQuestionBoardById(@PathVariable("id") Long id) {
        return questionBoardService.getQboardById(id);
    }


}
