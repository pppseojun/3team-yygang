package com.beyond3.yyGang.board.dto;

import com.beyond3.yyGang.board.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto{

    private Long id;

    private String title;

    private String content;

    private Long userid;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getBoardId();
        this.title = board.getTitle();
        this.content = board.getBoardContent();
        this.userid = board.getUser().getUserId();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }



}
