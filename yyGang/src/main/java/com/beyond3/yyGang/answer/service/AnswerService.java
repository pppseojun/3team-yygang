package com.beyond3.yyGang.answer.service;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.dto.AnswerRequestDto;
import com.beyond3.yyGang.answer.repository.AnswerRepository;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.q_board.repository.QuestionBoardRepository;
import com.beyond3.yyGang.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionBoardRepository questionBoardRepository;


    @Transactional
    public Answer saveAnswer(Long qboardId, AnswerRequestDto dto) {

        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(() -> new IllegalArgumentException("댓글을 쓸 게시글이 없는데요.."+qboardId));

        dto.setQboard(questionBoard);

        Answer answer = dto.toEntity();

        return answerRepository.save(answer);
    }


}
