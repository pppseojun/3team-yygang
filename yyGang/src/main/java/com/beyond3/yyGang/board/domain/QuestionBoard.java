package com.beyond3.yyGang.board.domain;

import com.beyond3.yyGang.member.domain.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "question_board")
public class QuestionBoard {
    // 약 질문 게시판

    @Id
    @GeneratedValue
    @Column(name = "qboard_id")
    private Long qboardId;  // 질문글 ID

    private String qboardTitle; // 글 제목

    @Column(columnDefinition = "TEXT")
    private String qboardContents; // 글 내용

    // 게시 날짜
    @Column(name = "qboard_date", updatable = false)
    private LocalDateTime qboardDate;

    // 수정할 때마다 매 번 업데이트 되는 값
    private LocalDateTime qboardMdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users; // 회원 ID만 받아오기

}
