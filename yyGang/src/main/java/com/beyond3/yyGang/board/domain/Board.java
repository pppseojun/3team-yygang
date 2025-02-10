package com.beyond3.yyGang.board.domain;

import com.beyond3.yyGang.member.domain.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String boardContents;

    private LocalDateTime boardDate; // 게시글 작성 날짜

    private LocalDateTime boardMdate; // 게시글 수정 날짜

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "board")
    private List<Comments> comments;

    @OneToMany(mappedBy = "qboard")
    private List<Answer> answers;


}
