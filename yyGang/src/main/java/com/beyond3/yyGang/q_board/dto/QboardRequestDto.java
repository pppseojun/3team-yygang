package com.beyond3.yyGang.q_board.dto;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QboardRequestDto {
    private String boardTitle;
    private String boardContent;
    private Long userId;

    @Builder
    public QboardRequestDto(String boardTitle, String boardContent, Long userId) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.userId = userId;
    }

    public QuestionBoard toEntity(User user) {
        return QuestionBoard.builder()
                .qboardTitle(this.boardTitle)
                .qboardContent(this.boardContent)
                .user(user)
                .build();
    }

}

