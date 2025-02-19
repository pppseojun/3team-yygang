package com.beyond3.yyGang.q_board.dto;

import com.beyond3.yyGang.q_board.QuestionBoard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QboardRequestDto {
    private String boardTitle;
    private String boardContent;
    private Long userId;
}

//public QuestionBoard toEntity(){
//    return QuestionBoard.
//}
