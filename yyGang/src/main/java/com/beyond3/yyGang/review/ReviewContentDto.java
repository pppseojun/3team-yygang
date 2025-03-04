package com.beyond3.yyGang.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewContentDto {

    private Long reviewId;  // 리뷰 ID

    private String userName; // 회원 - 외래키

    private Long nSupplementId;  // 상품 ID - 외래키

    private String content; // 리뷰 내용

    private LocalDateTime date; // 작성 날짜 -> 수정 시 작성 날짜 변경되게

    public ReviewContentDto(Review review) {
        this.reviewId = review.getReviewId();
        this.userName = review.getUser().getName();
        this.nSupplementId = review.getNSupplement().getProductId();
        this.content = review.getContent();
        this.date = review.getDate();
    }
}
