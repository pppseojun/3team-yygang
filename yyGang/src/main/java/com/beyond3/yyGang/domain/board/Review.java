package com.beyond3.yyGang.domain.board;

import com.beyond3.yyGang.domain.member.Users;
import com.beyond3.yyGang.domain.store.NSupplements;
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
@Table(name = "review")
public class Review {
    // 상품 리뷰 테이블

    @Id
    @GeneratedValue
    private Long reviewId;  // 리뷰 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "r_userId")
    private Users users; // 회원 - 외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_ID")
    private NSupplements nSupplements;  // 상품 ID - 외래키

    @Column(columnDefinition = "TEXT")
    private String rContents; // 리뷰 내용

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date; // 작성 날짜 -> 수정 시 작성 날짜 변경되게

}
