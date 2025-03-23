package com.beyond3.yyGang.board.service;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.board.repository.BoardLikeRepository;
import com.beyond3.yyGang.board.repository.BoardRepository;
import com.beyond3.yyGang.board.dto.BoardRequestDto;
import com.beyond3.yyGang.board.dto.BoardResponseDto;
import com.beyond3.yyGang.handler.exception.BoardException;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.ExceptionMessage;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardLikeRepository boardLikeRepository;


    public User userByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserException(ExceptionMessage.USER_NOT_FOUND));
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new BoardException(ExceptionMessage.CANNOT_FOUND_BOARD));
    }

    public void validateOwner(User user, Board board) {
        if (!user.getUserId().equals(board.getUser().getUserId())) {
            throw new BoardException(ExceptionMessage.CANNOT_EDIT_CONTENTS);
        }
    }

    // 게시글 등록
    @Override
    @Transactional
    public BoardResponseDto save(BoardRequestDto requestDto, Principal principal) {

        // 사용자 추출
        User user = userByPrincipal(principal);

        // 게시글 등록
        Board board = requestDto.toEntity(user);

        Board save = boardRepository.save(board);

        return new BoardResponseDto(save);
    }

    // 게시글 전체 조회
    @Override
    public Page<BoardResponseDto> findAll(int page, int size) {

        // 페이징 처리를 위한 예외 처리
        if(page < 0 || size <= 0){
            throw new BoardException(ExceptionMessage.INVALID_VALUE);
        }

        Pageable pageable = PageRequest.of(page, size);

        Page<Board> boardPage = boardRepository.findAll(pageable);

        // 게시판이 비어있는 경우
        if (boardPage.isEmpty()) {
            throw new BoardException(ExceptionMessage.NO_POSTS_EXIST);
        }

        return boardPage.map(BoardResponseDto::new);
    }

    // 게시글 수정
    @Override
    @Transactional
    // RequestDto 를 ModifyDto로 바꿔서 수정하는 편이 나음 -> 수정 사항이 없는 경우 그대로 넘어가도록
    public BoardResponseDto update(Principal principal, Long id, BoardRequestDto requestDto) {
        // 사용자 확인
        User user = userByPrincipal(principal);
        // 게시글 Id로 확인
        Board board = findBoardById(id);

        // 해당 게시글에 수정 권한이 있는지 확인
        validateOwner(user, board);

        // 권한이 있는 경우 수정하도록
        board.update(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 게시글 단건 조회
    @Override
    public BoardResponseDto findById(Long id) {
        Board boardById = findBoardById(id);
        return new BoardResponseDto(boardById);
    }

    // 게시글 삭제
    @Override
    @Transactional
    public void delete(Principal principal, Long id) {

        // 사용자 추출
        User user = userByPrincipal(principal);
        // 게시글 확인
        Board board = findBoardById(id);
        // 사용자가 해당 게시글에 대한 수정, 삭제 권한이 있는지?
        validateOwner(user, board);
        // 있으면 삭제
        boardRepository.deleteById(id);
    }

    // 좋아요 수 가져오기
    @Override
    public long getBoardLikeCount(Long boardId) {
        // 게시판 확인
        Board board = findBoardById(boardId);
        return boardLikeRepository.countByBoard(board);
    }
}
