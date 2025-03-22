package com.beyond3.yyGang.board.service;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.board.BoardLike;
import com.beyond3.yyGang.board.BoardLikeId;
import com.beyond3.yyGang.board.repository.BoardLikeRepository;
import com.beyond3.yyGang.board.repository.BoardRepository;
import com.beyond3.yyGang.handler.exception.BoardException;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.ExceptionMessage;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;


    @Override
    public String insert(Principal principal, Long boardId) {
        // 게시글 확인
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardException(ExceptionMessage.CANNOT_FOUND_BOARD));

        // 사용자 확인
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserException(ExceptionMessage.USER_NOT_FOUND));

        BoardLikeId boardLikeId = new BoardLikeId(user.getUserId(), board.getBoardId());

        BoardLike boardLike = new BoardLike(boardLikeId, user, board);

        if (boardLikeRepository.existsByUserAndBoard(user, board)) {
            boardLikeRepository.delete(boardLike);
            return "좋아요 취소";
        }

        boardLikeRepository.save(boardLike);

        return "좋아요 등록";
    }

}
