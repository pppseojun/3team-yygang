package com.beyond3.yyGang.board.service;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.board.dto.BoardRequestDto;
import com.beyond3.yyGang.board.dto.BoardResponseDto;
import org.springframework.data.domain.Page;

import java.security.Principal;

public interface BoardService {
    BoardResponseDto save(BoardRequestDto requestDto, Principal principal);

    Page<BoardResponseDto> findAll(int page, int size);

    BoardResponseDto update(Principal principal, Long id, BoardRequestDto requestDto);

    BoardResponseDto findById(Long id);

    void delete(Principal principal, Long id);

    long getBoardLikeCount(Long boardId);
}
