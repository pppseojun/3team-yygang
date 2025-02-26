package com.beyond3.yyGang.answer;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "answer")
@Entity
public class Answer {
    //약 질문 답변

    @Id
    @GeneratedValue
    private Long answerId;

    private String answerContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime answerDate;

    @UpdateTimestamp
    private LocalDateTime answerMdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qboard_id")
    private QuestionBoard qboard;

}
