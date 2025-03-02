package com.beyond3.yyGang.answer;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String answerContent;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime answerDate;

    @UpdateTimestamp
    private LocalDateTime answerMdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private com.beyond3.yyGang.user.domain.User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qboard_id")
    private QuestionBoard qboard;

    public void update(String answerContent) {
        this.answerContent = answerContent;
    }

}
