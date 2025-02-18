package com.beyond3.yyGang.q_board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionBoardService {
    private final QuestionBoardRepository questionBoardRepository;

    public QuestionBoardService(QuestionBoardRepository questionBoardRepository) {
        this.questionBoardRepository = questionBoardRepository;
    }

    // 게시글 저장
    public void saveQboard(QuestionBoard questionBoard) {
        questionBoardRepository.save(questionBoard);
//        return questionBoard.getQboardId();
    }

    // 전체 게시글 조회
    public List<QuestionBoard> getAllQboard() {
        return questionBoardRepository.findAll();
    }

    // 특정 ID로 게시글 조회
//    public QuestionBoard getQboardById(Long id) {
//        return questionBoardRepository.findById(id);
//    }



}
