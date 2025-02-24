package com.beyond3.yyGang.q_board;

import com.beyond3.yyGang.q_board.dto.QboardRequestDto;
import com.beyond3.yyGang.q_board.dto.QboardDto;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class QuestionBoardService {
    private final QuestionBoardRepository questionBoardRepository;
    private final UserRepository userRepository;

    public QuestionBoardService(QuestionBoardRepository questionBoardRepository, UserRepository userRepository) {
        this.questionBoardRepository = questionBoardRepository;
        this.userRepository = userRepository;
    }

    // 게시글 저장
//    @Transactional
//    public Long saveQboard(QboardRequestDto qboardRequestDto) {
//        Optional<User> user = userRepository.findById(qboardRequestDto.getUserId());
//
//
//        // 빌더패턴 사용하는것도 알아보기
////        QuestionBoard questionBoard = new QuestionBoard();
////        questionBoard.setQboardTitle(qboardRequestDto.getBoardTitle());
////        questionBoard.setQboardContents(qboardRequestDto.getBoardContent());
////        questionBoard.setUser(user);
////
////        questionBoardRepository.save(questionBoard);
////
////        return questionBoard.getQboardId();
//        return questionBoardRepository.save(QboardDto);
//    }

    // 전체 게시글 조회
    public List<QboardDto> getAllQboard() {
        List<QuestionBoard> qboardList = questionBoardRepository.findAll();

        return qboardList.stream().map(QboardDto::new).collect(Collectors.toList());

    }

//     특정 ID로 게시글 조회
//    public QuestionBoard getQboardById(Long id) {
//        return questionBoardRepository.findById(id);
//    }



}
