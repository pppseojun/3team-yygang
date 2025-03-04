package com.beyond3.yyGang.review;

import com.beyond3.yyGang.nsupplement.dto.NSupplementRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "특정 상품에 대한 리뷰 작성")
    public ResponseEntity<ReviewContentDto> registerReview(
            Principal principal,
            @RequestBody ReviewDto reviewDto) {

        String email = principal.getName();
        ReviewContentDto reviewContentDto = reviewService.registerReview(email, reviewDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewContentDto);
    }

    @DeleteMapping("/{productId}")   // 특정 상품에 대한 리뷰 -> 이건 상품 아이디로 해야하나?
    @Operation(summary = "리뷰 삭제", description = "특정 상품에 대한 리뷰 삭제")
    public ResponseEntity<String> deleteReview(
            Principal principal,
            @PathVariable("productId") Long productId) {

        String email = principal.getName();
        reviewService.deleteReview(email, productId);

        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

    @GetMapping("/{productId}")
    @Operation(summary = "상품 리뷰 조회", description = "특정 상품에 대한 리뷰 조회")
    public ResponseEntity<ProductReviewDto> viewReview(@PathVariable("productId") Long productId) {

        // 상품 리뷰는 딱히 사용자 인증 필요 x
        ProductReviewDto productReviewDto = reviewService.viewReview(productId);

        return ResponseEntity.ok(productReviewDto);
    }

    // 특정 상품 리뷰 수정
    @PostMapping("/{productId}")
    @Operation(summary = "리뷰 수정", description = "특정 상품에 대한 리뷰 수정")
    public ResponseEntity<ReviewContentDto> modifyReview(
            Principal principal,
            @PathVariable("productId") Long productId,
            @RequestBody ReviewDto reviewDto) {

        String email = principal.getName();
        ReviewContentDto reviewContentDto = reviewService.modifyReview(email, productId, reviewDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewContentDto);
    }
}
