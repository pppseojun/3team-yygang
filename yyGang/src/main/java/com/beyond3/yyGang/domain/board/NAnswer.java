package com.beyond3.yyGang.domain.board;

import com.beyond3.yyGang.domain.member.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "n_answer")
public class NAnswer {

    @Id
    @GeneratedValue
    @Column(name = "answer_ID")
    private Long answerID;  // 답변 ID

    @OneToOne
    @JoinColumn(name = "q_ID")
    private NQuestion question; // 질문 ID - 외래키

    @ManyToOne
    @JoinColumn(name = "seller_ID")
    private Users users; // 대답자 ID - 판매자 ID

    private String answerContents; // 응답 내용

    private LocalDateTime aDate; // 대답 날짜
}
