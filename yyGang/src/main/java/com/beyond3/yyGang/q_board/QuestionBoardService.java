package com.beyond3.yyGang.q_board;

import com.beyond3.yyGang.q_board.dto.QboardRequestDto;
import com.beyond3.yyGang.user.User;
import com.beyond3.yyGang.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionBoardService {
    private final QuestionBoardRepository questionBoardRepository;
    private final UserRepository userRepository;

    public QuestionBoardService(QuestionBoardRepository questionBoardRepository, UserRepository userRepository) {
        this.questionBoardRepository = questionBoardRepository;
        this.userRepository = userRepository;
    }

    // 게시글 저장
    public Long saveQboard(QboardRequestDto qboardRequestDto) {
        User user = userRepository.findById(qboardRequestDto.getUserId());

        // 빌더패턴 사용하는것도 알아보기
        QuestionBoard questionBoard = new QuestionBoard();
        questionBoard.setQboardTitle(qboardRequestDto.getBoardTitle());
        questionBoard.setQboardContents(qboardRequestDto.getBoardContent());
        questionBoard.setUser(user);

        questionBoardRepository.save(questionBoard);

        return questionBoard.getQboardId();
    }

    // 전체 게시글 조회
    public List<QuestionBoard> getAllQboard() {
        return questionBoardRepository.findAll();
    }

//     특정 ID로 게시글 조회
    public QuestionBoard getQboardById(Long id) {
        return questionBoardRepository.findById(id);
    }



}
