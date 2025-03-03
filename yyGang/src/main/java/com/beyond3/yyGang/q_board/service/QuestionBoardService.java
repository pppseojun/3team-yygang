package com.beyond3.yyGang.q_board.service;

import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import com.beyond3.yyGang.q_board.dto.QboardRequestDto;
import com.beyond3.yyGang.q_board.dto.QboardResponseDto;
import com.beyond3.yyGang.q_board.dto.QboardUpdateDto;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.q_board.repository.QuestionBoardRepository;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionBoardService {
    private final QuestionBoardRepository questionBoardRepository;
    private final UserRepository userRepository;

//    public static ResponseEntity<List<QboardResponseDto>> getQboard(int page, String criteria) {
//        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, criteria));
//        Page<QboardResponseDto> page = questionBoardRepository.findAll(pageable).map(QboardResponseDto::new);
//        return ResponseEntity.ok(page.getContent());
//    }

    // 게시글 저장
    @Transactional
    public void saveQboard(QboardRequestDto requestDto) {
        // 유효성 검사해서 유저 체크 -> 기능 동작
         User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new QuestionBoardException(ExceptionMessage.NOT_FOUND_USER));

         if(requestDto.getBoardContent().isEmpty()){
             throw new QuestionBoardException(ExceptionMessage.EMPTY_CONTENT);
         } else if (requestDto.getBoardTitle().isEmpty()) {
             throw new QuestionBoardException(ExceptionMessage.EMPTY_TITLE);
         }

        QuestionBoard qboard = requestDto.toEntity(user);
        questionBoardRepository.save(qboard);
    }

    // 전체 게시글 조회
    @Transactional
    public List<QboardResponseDto> getAllQboard() {
        List<QuestionBoard> qboardList = questionBoardRepository.findAll();

        // 예외 문서 작성예정
        if (qboardList.isEmpty()) {
            throw new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD);
        }

        return qboardList.stream()
                .map(QboardResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 ID로 게시글 조회
    @Transactional
    public QboardResponseDto getQboardById(Long qboardId) {

        Optional<QuestionBoard> questionBoard =  questionBoardRepository.findById(qboardId);

        return questionBoard.map(QboardResponseDto::new).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));
    }

    // 특정 게시글 수정
    @Transactional
    public void updateQboard(Long qboardId, QboardUpdateDto requestDto) {

        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));

        if (requestDto.getTitle().isEmpty()) {
            throw new QuestionBoardException(ExceptionMessage.EMPTY_TITLE);
        } else if (requestDto.getContent().isEmpty()) {
            throw new QuestionBoardException(ExceptionMessage.EMPTY_CONTENT);
        }

        questionBoard.update(requestDto.getTitle(), requestDto.getContent());
    }

    // 게시글 삭제
    public void deleteQboard(Long qboardId) {
        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(()-> new QuestionBoardException(ExceptionMessage.NOT_FOUND_QUESTION_BOARD));
        questionBoardRepository.delete(questionBoard);
    }
}
