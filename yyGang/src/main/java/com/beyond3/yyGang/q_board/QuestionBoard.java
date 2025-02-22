package com.beyond3.yyGang.q_board;

import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "question_board")
public class QuestionBoard {
    // 약 질문 게시판

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qboard_id")
    private Long qboardId;  // 질문글 ID

    private String qboardTitle; // 글 제목

    @Column(columnDefinition = "TEXT")
    private String qboardContent; // 글 내용

    // 게시 날짜
    @CreationTimestamp
    @Column(name = "qboard_date", updatable = false)
    private LocalDateTime qboardDate;

    // 수정할 때마다 매 번 업데이트 되는 값
    @UpdateTimestamp
    private LocalDateTime qboardMdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 회원 ID만 받아오기

}
