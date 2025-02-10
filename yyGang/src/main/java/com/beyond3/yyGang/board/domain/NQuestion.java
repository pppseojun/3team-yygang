package com.beyond3.yyGang.board.domain;

import com.beyond3.yyGang.store.domain.NSupplements;
import com.beyond3.yyGang.member.domain.Users;
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
@Table(name = "n_question")
public class NQuestion {
    // 영양제 관련 문의사항

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long questionId; // 문의 사항 ID

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private NSupplements supplements;

    @Column(name = "q_date")
    private LocalDateTime qDate; // 질문 작성 날짜

    @Column(columnDefinition = "TEXT")
    private String qContents; // 질문 내용

    @OneToOne(mappedBy = "question")
    private NAnswer nAnswer;
}
