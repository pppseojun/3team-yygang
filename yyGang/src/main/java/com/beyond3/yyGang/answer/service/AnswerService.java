package com.beyond3.yyGang.answer.service;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.answer.dto.AnswerRequestDto;
import com.beyond3.yyGang.answer.dto.AnswerResponseDto;
import com.beyond3.yyGang.answer.repository.AnswerRepository;
import com.beyond3.yyGang.common.AnswerException;
import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
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
        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(() -> new AnswerException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        // 사용자 정보 가져오기
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(()->new AnswerException(ExceptionMessage.NOT_FOUND_USER));

        // 답글 내용이 없을 경우
        if (requestDto.getAnswerContent().isEmpty()){
            throw new AnswerException(ExceptionMessage.EMPTY_CONTENT);
        }

        requestDto.setQboardId(questionBoard.getQboardId());

        Answer answer = requestDto.toEntity(questionBoard, user);

        answerRepository.save(answer);

    }

    // 답글 ID로 답글 조회
    @Transactional
    public List<AnswerResponseDto> getAnswerById(Long id) {

        Optional<Answer> answers = answerRepository.findById(id);
        if (answers.isEmpty()){
            throw new AnswerException(ExceptionMessage.NOT_FOUND_ANSWER);
        }

        return answers.stream().map(AnswerResponseDto::new).collect(Collectors.toList());
    }

    // 약 질문 ID로 답글 조회
    @Transactional
    public List<AnswerResponseDto> getAnswerByBoardId(Long qboardId) {
         QuestionBoard questionBoard =  questionBoardRepository.findById(qboardId).orElseThrow(() -> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        return questionBoard.getAnswers().stream().map(AnswerResponseDto::new).collect(Collectors.toList());
    }

    // 수정 로직 수정해보기
    @Transactional
    public void updateAnswer(Long qboardId,Long answerId,AnswerRequestDto requestDto) {

        Answer answer = answerRepository.findByAnswerIdAndQboard_QboardId(answerId,qboardId).orElseThrow(()->new AnswerException(ExceptionMessage.BAD_REQUEST_ANSWER));

//        Answer findAnswer = answerRepository.findById(answerId).orElseThrow(()->new AnswerException(ExceptionMessage.NOT_FOUND_ANSWER));

        answer.update(requestDto.getAnswerContent());
    }

    // 삭제 에러 로직 수정하기
    public void deleteAnswer(Long qboardId, Long answerId) {
        Answer answer = answerRepository.findByAnswerIdAndQboard_QboardId(answerId,qboardId).orElseThrow(()->new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        answerRepository.delete(answer);

    }
}
