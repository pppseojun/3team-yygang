package com.beyond3.yyGang.nsupplement.controller;
import com.beyond3.yyGang.nsupplement.dto.NSupplementModifyDto;
import com.beyond3.yyGang.nsupplement.service.NSupplementService;
import com.beyond3.yyGang.nsupplement.dto.NSupplementRegisterDto;
import com.beyond3.yyGang.review.dto.ReviewRequestDto;
import com.beyond3.yyGang.review.dto.ReviewResponseDto;
import com.beyond3.yyGang.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nsupplement")
@RequiredArgsConstructor
@Tag(name = "Nsupplement", description = "영양제 관련 기능")
public class NSupplementController {

    private final NSupplementService nSupplementService;
    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "상품 등록", description = "SELLER 만 상품 등록이 가능하다.")
    public ResponseEntity<String> register(
            Principal principal,
            @RequestBody NSupplementRegisterDto nSupplementRegisterDto) {

        String email = principal.getName();
        nSupplementService.registerProduct(email, nSupplementRegisterDto);

        return ResponseEntity.ok("상품 등록이 완료되었습니다.");
    }

    @GetMapping
    @Operation(summary = "상품 확인", description = "판매자만 상품 조회 가능 / 본인이 등록한 상품만 조회 가능")
    // Seller 입장에서 등록한 상품들 확인
    public ResponseEntity<List<NSupplementRegisterDto>> info(
            Principal principal
    ) {
        String email = principal.getName();
        List<NSupplementRegisterDto> allNSupplements = nSupplementService.getAllNSupplements(email);

        return ResponseEntity.ok(allNSupplements);
    }

    @DeleteMapping("/{nSupplementId}")
    @Operation(summary = "상품 삭제", description = "해당 상품을 등록한 판매자만 삭제 가능")
    public ResponseEntity<String> delete(
            Principal principal,
            @PathVariable("nSupplementId") Long nSupplementId
    ){
        String email = principal.getName();
        nSupplementService.deleteProduct(email, nSupplementId);

        return ResponseEntity.ok("상품 삭제가 완료되었습니다.");
    }

    @PostMapping("/{nSupplementId}")
    @Operation(summary = "상품 수정", description = "해당 상품을 등록한 판매자만 수정 가능")
    public ResponseEntity<String> modify(
            Principal principal,
            @PathVariable("nSupplementId") Long nSupplementId,
            @RequestBody NSupplementModifyDto nSupplementModifyDto
    ){
        String email = principal.getName();
        nSupplementService.modifyProduct(email, nSupplementId,nSupplementModifyDto);

        return ResponseEntity.ok("상품 수정이 완료되었습니다.");
    }

    // 특정 상품 리뷰 조회  ->  페이징 처리
    @GetMapping("/{nSupplementId}/review")
    @Operation(summary = "상품 리뷰 조회", description = "특정 상품에 대한 모든 리뷰 조회")
    public ResponseEntity<List<ReviewResponseDto>> viewReview(
            @PathVariable("nSupplementId") Long productId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        // 특정 상품에 대한 리뷰이기 때문에 사용자는 필요 x
        List<ReviewResponseDto> reviewResponseDtos = reviewService.viewReview(productId, page, size);

        // 전체 리뷰들이 보이도록
        return ResponseEntity.ok(reviewResponseDtos);
    }

    // 리뷰 삭제
    @DeleteMapping("/{nSupplementId}/review")
    @Operation(summary = "리뷰 삭제", description = "특정 회원이 작성한 특정 상품에 대한 리뷰 삭제")
    public ResponseEntity<String> deleteReview(
            Principal principal,
            @PathVariable("nSupplementId") Long productId) {

        String email = principal.getName();
        reviewService.deleteReview(email, productId);

        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

    // 리뷰 작성
    @PostMapping("/{nSupplementId}/review")
    @Operation(summary = "리뷰 작성", description = "특정 회원이 특정 상품에 대한 리뷰 작성")
    public ResponseEntity<ReviewResponseDto> registerReview(
            Principal principal,
            @PathVariable("nSupplementId") Long productId,
            @RequestBody ReviewRequestDto reviewRequestDto) {

        String email = principal.getName();
        ReviewResponseDto reviewResponseDto = reviewService.registerReview(email, reviewRequestDto, productId);

        // 리뷰 작성 후 본인이 작성한 리뷰가 보이도록
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewResponseDto);
    }

    // 특정 상품 리뷰 수정
    @PutMapping("/{nSupplementId}/review")
    @Operation(summary = "리뷰 수정", description = "특정 사용자가 작성한 특정 상품에 대한 리뷰 수정")
    public ResponseEntity<ReviewResponseDto> modifyReview(
            Principal principal,
            @PathVariable("nSupplementId") Long productId,
            @RequestBody ReviewRequestDto reviewRequestDto) {

        String email = principal.getName();
        ReviewResponseDto reviewResponseDto = reviewService.modifyReview(email, productId, reviewRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewResponseDto);
    }

}
