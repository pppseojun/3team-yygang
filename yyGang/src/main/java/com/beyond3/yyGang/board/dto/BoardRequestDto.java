package com.beyond3.yyGang.board.dto;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {

    @NotBlank(message = "제목 작성은 필수입니다.")
    private String title;

    @NotBlank(message = "내용 작성은 필수입니다.")
    private String content;

    private Long userId;    // Principal로 받기 때문에 크게 필요하진 않을 것 같음

    public Board toEntity(User user){
        return Board.builder()
                .title(this.title)
                .boardContent(this.content)
                .user(user)
                .build();
    }

}
