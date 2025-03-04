package com.beyond3.yyGang.review;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewDto {

    private Long productId;  // 상품 아이디

    private List<ReviewContentDto> reviewContentDtoList;   // 리뷰 목록
}
