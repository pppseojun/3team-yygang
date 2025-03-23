package com.beyond3.yyGang.board.controller;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.board.dto.BoardRequestDto;
import com.beyond3.yyGang.board.dto.BoardResponseDto;
import com.beyond3.yyGang.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.beyond3.yyGang.board.QBoard.board;

@Tag(name = "Board", description = "자유 게시판")
@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 등록
    @PostMapping
    @Operation(summary = "게시글 등록", description = "사용자가 게시판에 게시글을 등록한다.")
    public ResponseEntity<BoardResponseDto> save(
            Principal principal,
            @RequestBody @Valid BoardRequestDto requestDto) {

        BoardResponseDto save = boardService.save(requestDto, principal);
        return ResponseEntity.ok(save);
    }

    // 게시글 전체 조회
    @GetMapping
    @Operation(summary = "게시글 조회", description = "사용자가 게시글 전체를 조회한다.")
    public ResponseEntity<List<BoardResponseDto>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size ){

        Page<BoardResponseDto> boardList = boardService.findAll(page, size);
        return ResponseEntity.ok(boardList.getContent());

    }

    // 게시글 단건 조회
    @GetMapping("/{BoardId}")
    @Operation(summary = "게시글 상세조회", description = "특정 게시글을 조회한다.")
    public ResponseEntity<BoardResponseDto> findById(
            @PathVariable("BoardId") Long BoardId){

        BoardResponseDto dto = boardService.findById(BoardId);

        return ResponseEntity.ok(dto);
    }

    // modify dto 하나 새로 만드는 편이 좋을듯
    @PutMapping("/{BoardId}")
    @Operation(summary = "수정", description = "게시글 수정")
    public ResponseEntity<BoardResponseDto> update(
            Principal principal,
            @PathVariable("BoardId") Long BoardId,
            @RequestBody BoardRequestDto requestDto){

        BoardResponseDto board = boardService.update(principal, BoardId, requestDto);
        return ResponseEntity.ok(board);
    }

    // id 보다 String으로 삭제되었습니다-는 어때?
    @DeleteMapping("/{BoardId}")
    @Operation(summary = "삭제", description = "게시글 삭제")
    public ResponseEntity<Long> delete(
            Principal principal,
            @PathVariable("BoardId") Long BoardId){

        boardService.delete(principal, BoardId);
        return ResponseEntity.ok(BoardId);
    }

    @GetMapping("/{BoardId}/likes")
    @Operation(summary = "좋아요 수", description = "게시글 좋아요 수")
    public ResponseEntity<Long> getBoardLikeCount(
            @PathVariable("BoardId") Long boardId){
        long likeCount = boardService.getBoardLikeCount(boardId);
        return ResponseEntity.ok(likeCount);
    }
}
