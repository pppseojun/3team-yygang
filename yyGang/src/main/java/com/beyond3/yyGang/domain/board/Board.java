package com.beyond3.yyGang.domain.board;

import com.beyond3.yyGang.domain.member.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Board")
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_ID")
    private Long boardID;

    @Column(name = "board_title")
    private String title;

    private String boardContents;

    private LocalDateTime boardDate; // 게시글 작성 날짜

    private LocalDateTime boardMdate; // 게시글 수정 날짜

    @ManyToOne
    @JoinColumn(name = "b_userID")
    private Users users;


}
