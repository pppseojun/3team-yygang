package com.beyond3.yyGang.nsupplement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NSupplementResponseDto {

    private String productName; // 상품 이름

    private String caution; // 주의 사항

    private String brand;  // 브랜드

    private int price;  // 상품 가격

    private int reviewCount;    // 전체 리뷰 수

    private List<String> ingredients = new ArrayList<>();

    private List<String> healthNames = new ArrayList<>();

}
