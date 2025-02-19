package com.beyond3.yyGang.q_board.dto;

import com.beyond3.yyGang.q_board.QuestionBoard;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class qboardDto {
    private Long qboardId;
    private String qboardTitle;
    private String qboardContents;
    private LocalDateTime qboardDate;
    private LocalDateTime qboardMdate;
    private Long userId;

    public qboardDto(QuestionBoard questionBoard) {
        this.qboardId = questionBoard.getQboardId();
        this.qboardTitle = questionBoard.getQboardTitle();
        this.qboardContents = questionBoard.getQboardContents();
        this.qboardDate = questionBoard.getQboardDate();
        this.qboardMdate = questionBoard.getQboardMdate();
        this.userId = (questionBoard.getUser() != null) ? questionBoard.getUser().getUserId() : null;
    }
}
