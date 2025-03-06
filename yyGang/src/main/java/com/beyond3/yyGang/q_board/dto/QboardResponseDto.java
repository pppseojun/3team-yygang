package com.beyond3.yyGang.q_board.dto;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QboardResponseDto {
    private Long qboardId;
    private String qboardTitle;
    private String qboardContent;
    private LocalDateTime qboardDate;
    private LocalDateTime qboardMdate;
    private Long userId;
//    private List<AnswerResponseDto> answers;

    @Builder
    public QboardResponseDto(QuestionBoard questionBoard) {
        this.qboardId = questionBoard.getQboardId();
        this.qboardTitle = questionBoard.getQboardTitle();
        this.qboardContent = questionBoard.getQboardContent();
        this.qboardDate = questionBoard.getCreatedAt();
        this.qboardMdate = questionBoard.getModifiedAt();
        this.userId = (questionBoard.getUser() != null) ? questionBoard.getUser().getUserId() : null;
//        this.answers = questionBoard.getAnswers().stream().map(AnswerResponseDto::new).collect(Collectors.toList());
    }
}
