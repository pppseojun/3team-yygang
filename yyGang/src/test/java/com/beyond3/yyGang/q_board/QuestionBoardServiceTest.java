package com.beyond3.yyGang.q_board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class QuestionBoardServiceTest {

    @Autowired QuestionBoardService questionBoardService;
    @Autowired QuestionBoardRepository questionBoardRepository;

    @Test
    public void 게시글저장(){
        // given
        QuestionBoard questionBoardinfo = new QuestionBoard();
        questionBoardinfo.setQboardId(100L);
        questionBoardinfo.setQboardTitle("test");

        //when
//        QuestionBoard questionBoard = questionBoardService.saveQboard(questionBoardinfo);

        //then
//        assertEquals("테스트 제목",questionBoard.getQboardTitle());

    }



}