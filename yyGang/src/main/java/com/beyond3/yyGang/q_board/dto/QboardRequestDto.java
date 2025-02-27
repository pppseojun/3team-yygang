package com.beyond3.yyGang.q_board.dto;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QboardRequestDto {
    private String boardTitle;
    private String boardContent;
    private Long userId;

    public QuestionBoard toEntity(User user) {
        return QuestionBoard.builder()
                .qboardTitle(this.boardTitle)
                .qboardContent(this.boardContent)
                .user(user)
                .build();
    }

}

