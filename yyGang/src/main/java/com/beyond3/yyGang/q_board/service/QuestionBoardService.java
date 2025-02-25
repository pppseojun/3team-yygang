package com.beyond3.yyGang.q_board.service;

import com.beyond3.yyGang.q_board.dto.QboardRequestDto;
import com.beyond3.yyGang.q_board.dto.QboardResponseDto;
import com.beyond3.yyGang.q_board.dto.QboardUpdateDto;
import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.q_board.repository.QuestionBoardRepository;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + requestDto.getUserId()));

        QuestionBoard qboard = requestDto.toEntity(user);
        questionBoardRepository.save(qboard);
    }

    // 전체 게시글 조회
    @Transactional
    public List<QboardResponseDto> getAllQboard() {
        List<QuestionBoard> qboardList = questionBoardRepository.findAll();

        return qboardList.stream()
                .map(QboardResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 ID로 게시글 조회
    @Transactional
    public QboardResponseDto getQboardById(Long qboardId) {

        Optional<QuestionBoard> questionBoard =  questionBoardRepository.findById(qboardId);

        return questionBoard.map(QboardResponseDto::new).orElseThrow(()-> new IllegalArgumentException("게시글이 없습니다. " + qboardId));
    }

    // 특정 게시글 수정
    @Transactional
    public void updateQboard(Long qboardId, QboardUpdateDto requestDto) {
        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(()-> new IllegalArgumentException("게시글이 없습니다. " + qboardId));
        questionBoard.update(requestDto.getTitle(), requestDto.getContent());
    }


    // 게시글 삭제
    public void deleteQboard(Long qboardId) {
        QuestionBoard questionBoard = questionBoardRepository.findById(qboardId).orElseThrow(()-> new IllegalArgumentException("게시글이 없습니다. " + qboardId));
        questionBoardRepository.delete(questionBoard);
    }
}
