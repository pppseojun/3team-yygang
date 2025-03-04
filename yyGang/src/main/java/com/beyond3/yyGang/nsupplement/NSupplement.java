package com.beyond3.yyGang.nsupplement;

import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.nsupplement.dto.NSupplementModifyDto;
import com.beyond3.yyGang.nsupplement.dto.NSupplementRegisterDto;
import com.beyond3.yyGang.review.Review;
import com.beyond3.yyGang.user.domain.Role_name;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.dto.UserModifyDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "n_supplement")
public class NSupplement {

    // 영양제
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;  // 상품 아이디

    private String productName; // 상품 이름

    @Column(columnDefinition = "TEXT")
    private String caution; // 주의 사항

    private String brand;  // 브랜드

    private int price;  // 상품 가격

    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "nSupplement")
    private List<Review> reviews;

    public void updateNSupplement(NSupplementModifyDto dto) {
        // null이거나 값이 비어 있는 경우는 업데이트 안되게ㅇㅇ

        if(dto.getProductName() != null && !dto.getProductName().trim().isEmpty()){
            this.productName = dto.getProductName();
        }

        if(dto.getCaution() != null && !dto.getCaution().trim().isEmpty()){
            this.caution = dto.getCaution();
        }

        if(dto.getBrand() != null && !dto.getBrand().trim().isEmpty()){
            this.brand = dto.getBrand();
        }

        Optional.of(dto.getPrice()).ifPresent(this::setPrice);
        Optional.of(dto.getStockQuantity()).ifPresent(this::setStockQuantity);
    }

    public void decreaseStockQuantity(int quantity){
        if(stockQuantity - quantity < 0){
            throw new IllegalStateException("재고가 충분하지 않습니다.");
        }
        this.stockQuantity -= quantity;
    }

    public void increaseStockQuantity(int quantity){
        this.stockQuantity += quantity;
    }
}
