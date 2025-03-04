package com.beyond3.yyGang.nsupplement.dto;

import com.beyond3.yyGang.hfunction.HFunctionName;
import com.beyond3.yyGang.hfunction.HFunctionalItem;
import com.beyond3.yyGang.ingredient.IngredientName;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.user.domain.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NSupplementRegisterDto {

    @NotEmpty(message = "상품명은 필수입니다.")
    private String productName; // 상품 이름

    private String caution; // 주의 사항

    @NotEmpty(message = "브랜드 명은 필수입니다.")
    private String brand;  // 브랜드

    @NotEmpty(message = "상품 가격은 필수입니다.")
    private int price;  // 상품 가격

    private int stockQuantity;

    @NotEmpty(message = "성분은 필수 입니다.")
    private List<IngredientName> ingredients;

    @NotEmpty(message = "건강 기능 입력은 필수 입니다.")
    private List<HFunctionName> hFunctionalItems;

    public NSupplement toEntity(User user) {
        return NSupplement.builder()
                .productName(productName)
                .caution(caution)
                .brand(brand)
                .price(price)
                .seller(user)
                .stockQuantity(stockQuantity)
                .build();
    }

    // NSupplement를 받아서 생성자 호출
    public NSupplementRegisterDto(NSupplement supplement) {
        this.productName = supplement.getProductName();
        this.caution = supplement.getCaution();
        this.brand = supplement.getBrand();
        this.price = supplement.getPrice();
        this.stockQuantity = supplement.getStockQuantity();
    }
}
