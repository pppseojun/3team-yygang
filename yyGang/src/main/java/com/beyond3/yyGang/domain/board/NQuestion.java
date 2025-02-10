package com.beyond3.yyGang.domain.board;

import com.beyond3.yyGang.domain.store.NSupplements;
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
@Table(name = "n_question")
public class NQuestion {
    // 영양제 관련 문의사항

    @Id
    @GeneratedValue
    @Column(name = "q_ID")
    private Long qID; // 문의 사항 ID

    @ManyToOne
    @JoinColumn(name = "customer_ID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "products_ID")
    private NSupplements supplements;

    @Column(name = "q_date")
    private LocalDateTime qDate; // 질문 작성 날짜

    private String qContents; // 질문 내용

    @OneToOne(mappedBy = "question")
    private NAnswer nAnswer;
}
