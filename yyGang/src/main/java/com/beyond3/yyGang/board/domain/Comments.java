package com.beyond3.yyGang.board.domain;

import com.beyond3.yyGang.member.domain.Users;
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
@Table(name = "comment")
public class Comments {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long commentId;

    @Column(columnDefinition = "TEXT")
    private String commentContents;

    private LocalDateTime commentDate;

    private LocalDateTime commentMdate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
