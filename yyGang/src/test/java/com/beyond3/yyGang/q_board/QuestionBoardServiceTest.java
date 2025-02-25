package com.beyond3.yyGang.q_board;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.q_board.repository.QuestionBoardRepository;
import com.beyond3.yyGang.q_board.service.QuestionBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class QuestionBoardServiceTest {

    @Autowired
    QuestionBoardService questionBoardService;
    @Autowired
    QuestionBoardRepository questionBoardRepository;

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