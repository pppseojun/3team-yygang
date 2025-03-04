package com.beyond3.yyGang.review;


import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 리뷰 작성 Dto
public class ReviewDto {

    private Long nSupplementId;

    private String content; // 리뷰 내용

    public Review toEntity(User user, NSupplement nSupplement) {
        return Review.builder()
                .user(user)
                .nSupplement(nSupplement)
                .content(content)
                .build();
    }

}
