package com.beyond3.yyGang.answer.service;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.dto.AnswerRequestDto;
import com.beyond3.yyGang.answer.dto.AnswerResponseDto;
import com.beyond3.yyGang.answer.repository.AnswerRepository;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.q_board.repository.QuestionBoardRepository;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionBoardRepository questionBoardRepository;
    private final UserRepository userRepository;


    // 답글 저장
    @Transactional
    public void saveAnswer(Long qboardId, AnswerRequestDto requestDto) {

        // 게시판 id 찾아오기
        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(() -> new IllegalArgumentException("댓글을 쓸 게시글이 없는데요.."+qboardId));

        // 사용자 정보 가져오기
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(()->new IllegalArgumentException("게시글 회원이 없는디여.."));

        requestDto.setQboardId(questionBoard.getQboardId());

        Answer answer = requestDto.toEntity(questionBoard, user);

        answerRepository.save(answer);

    }

    // 답글 ID로 답글 조회
    @Transactional
    public List<AnswerResponseDto> getAnswerById(Long id) {
        Optional<Answer> answers =  answerRepository.findById(id);

        return answers.stream().map(AnswerResponseDto::new).collect(Collectors.toList());
    }

    // 약 질문 ID로 답글 조회
    @Transactional
    public List<AnswerResponseDto> getAnswerByBoardId(Long qboardId) {
         QuestionBoard questionBoard =  questionBoardRepository.findById(qboardId).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다,"));

        return questionBoard.getAnswers().stream().map(AnswerResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateAnswer(Long qboardId,Long answerId,AnswerRequestDto requestDto) {

        Answer answer = answerRepository.findByAnswerIdAndQboard_QboardId(answerId,qboardId).orElseThrow(()->new IllegalArgumentException("조회한 답글이 없는데여.. 수정 실패"));

        answer.update(requestDto.getAnswerContent());

    }

    public void deleteAnswer(Long qboardId, Long answerId) {
        Answer answer = answerRepository.findByAnswerIdAndQboard_QboardId(answerId,qboardId).orElseThrow(()->new IllegalArgumentException("해당 게시글은 존재하지 않아요 삭제 실패"));

        answerRepository.delete(answer);

    }
}
