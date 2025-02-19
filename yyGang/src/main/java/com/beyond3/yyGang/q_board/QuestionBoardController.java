package com.beyond3.yyGang.q_board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //게시글 작성
//    @PostMapping
    @GetMapping("/write")
    public String createQuestionBoard( QuestionBoard questionBoard) {

//        questionBoardService.saveQboard(questionBoard);
//        return ResponseEntity.ok().build();
        return "qboard";
    }

    // 게시글 조회
    @GetMapping
    public List<QuestionBoard> getAllQuestionBoard() {
        return questionBoardService.getAllQboard();
    }

    // 특정 ID로 게시글 조회
//    @GetMapping("/{id}")
//    public ResponseEntity<QuestionBoard> getQuestionBoardById(@PathVariable Long id) {
//        QuestionBoard questionBoard = questionBoardService.getQboardById(id);
//        if (questionBoard == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(questionBoard);
//    }


}
