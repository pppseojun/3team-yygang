package com.beyond3.yyGang;

import com.beyond3.yyGang.q_board.QuestionBoard;
import com.beyond3.yyGang.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "answer")
public class Answer {
    //약 질문 답변

    @Id
    @GeneratedValue
    private Long answerId;

    private String answerContents;

    @CreationTimestamp
    private LocalDateTime answerDate;
    @UpdateTimestamp
    private LocalDateTime answerMdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qboard_id")
    private QuestionBoard qboard;

}
