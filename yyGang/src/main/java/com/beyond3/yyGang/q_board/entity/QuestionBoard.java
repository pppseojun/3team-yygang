package com.beyond3.yyGang.q_board.entity;

import com.beyond3.yyGang.answer.Answer;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 수정할 때마다 매 번 업데이트 되는 값
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 회원 ID만 받아오기

    @OneToMany(mappedBy = "qboard",cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Builder
    public QuestionBoard(Long qboardId, String qboardTitle, String qboardContent, LocalDateTime qboardDate, LocalDateTime qboardMdate, User user) {
        this.qboardId = qboardId;
        this.qboardTitle = qboardTitle;
        this.qboardContent = qboardContent;
        this.createdAt = qboardDate;
        this.modifiedAt = qboardMdate;
        this.user = user;
    }

    public void update(String qboardTitle, String qboardContent){
        this.qboardTitle = qboardTitle;
        this.qboardContent = qboardContent;
    }
}
