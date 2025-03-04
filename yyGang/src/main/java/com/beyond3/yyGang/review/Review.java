package com.beyond3.yyGang.review;

import com.beyond3.yyGang.nsupplement.NSupplement;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review {

    // 상품 리뷰 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;  // 리뷰 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 회원 - 외래키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private NSupplement nSupplement;  // 상품 ID - 외래키

    @Column(columnDefinition = "TEXT")
    private String content; // 리뷰 내용

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date; // 작성 날짜 -> 수정 시 작성 날짜 변경되게

    public void setContent(String content) {
        this.content = content;
    }
}
