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
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long answerId;

    @Column(columnDefinition = "TEXT")
    private String answerContents;

    private LocalDateTime answerDate;

    private LocalDateTime answerMdate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "qboard_id")
    private QuestionBoard qboard;
}
