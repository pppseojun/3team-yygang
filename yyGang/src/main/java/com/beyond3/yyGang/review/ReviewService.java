package com.beyond3.yyGang.review;

import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.user.domain.Role_name;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import com.beyond3.yyGang.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final NSupplementRepository nSupplementRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewContentDto registerReview(String email, ReviewDto reviewDto) {
        // 회원 추출
        User user = extractedUser(email);

        // 상품 추출
        NSupplement product = nSupplementRepository.findByproductId(reviewDto.getNSupplementId())
                .orElseThrow(() -> new UserException(UserExceptionMessage.NO_REGISTERED_PRODUCTS));

        // 해당 회원이 해당 상품에 대한 리뷰를 이미 작성했는지?   -> 양방향도 안쓰고 그냥 좀 더 간단한 방법 없나...
        List<Review> reviewByUser = reviewRepository.findByUser(user);
        for (Review review : reviewByUser) {
            if(review.getNSupplement().equals(product)){
                throw new UserException(UserExceptionMessage.ALREADY_REVIEWED);
            }
        }

        // 리뷰를 작성하지 않았다면 -> 리뷰 저장
        Review review = reviewDto.toEntity(user, product);
        // review 저장
        Review savedReview = reviewRepository.save(review);

        return new ReviewContentDto(savedReview);
    }

    @Transactional
    public void deleteReview(String email, Long productId) {
        // 사용자 추출
        User user = extractedUser(email);

        // 상품 추출
        NSupplement product = nSupplementRepository.findByproductId(productId)
                .orElseThrow(() -> new UserException(UserExceptionMessage.NO_REGISTERED_PRODUCTS));

        // 해당 회원이 해당 상품에 대해 작성한 리뷰 가져오기
        List<Review> reviewByUser = reviewRepository.findByUser(user);
        Review delReview = null;

        for (Review review : reviewByUser) {
            if(review.getNSupplement().equals(product)){
                delReview = review;
            }
        }

        if(delReview == null){
            throw new UserException(UserExceptionMessage.REVIEW_NOT_FOUND);
        }

        // 리뷰를 찾았으면 리뷰 삭제하기
        reviewRepository.delete(delReview);
    }

    // 특정 상품에 대한 리뷰 조회
    @Transactional
    public ProductReviewDto viewReview(Long productId) {
        // 상품 추출
        NSupplement product = nSupplementRepository.findByproductId(productId)
                .orElseThrow(() -> new UserException(UserExceptionMessage.NO_REGISTERED_PRODUCTS));

        List<Review> reviews = reviewRepository.findByNSupplement(product);
        List<ReviewContentDto> reviewContentDtos = reviews.stream().map(ReviewContentDto::new).toList();

        return new ProductReviewDto(productId, reviewContentDtos);
    }

    @Transactional  // 리뷰 수정
    public ReviewContentDto modifyReview(String email, Long productId, ReviewDto reviewDto) {
        // 사용자 추출
        User user = extractedUser(email);

        // 상품 추출
        NSupplement product = nSupplementRepository.findByproductId(productId)
                .orElseThrow(() -> new UserException(UserExceptionMessage.NO_REGISTERED_PRODUCTS));

        // 작성한 리뷰 확인
        Review review = reviewRepository.findByUserAndNSupplement(user, product)
                .orElseThrow(() -> new UserException(UserExceptionMessage.REVIEW_NOT_FOUND));

        review.setContent(reviewDto.getContent());

        return new ReviewContentDto(review);
    }

    // 사용자 추출 -> 역할이 SELLER일 때만
    private User extractedUser(String email) {
        // 사용자 확인
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserExceptionMessage.USER_NOT_FOUND));
    }
}
