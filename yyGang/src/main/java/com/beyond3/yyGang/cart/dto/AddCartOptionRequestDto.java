package com.beyond3.yyGang.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCartOptionRequestDto {

    // 상품 아이디는 선택한 순간 바로 입력되도록 프론트 엔드에서 설정 ->
    private Long nSupplementId;

    // 개수 수정이 없는 경우 -> 기존 개수 그대로 유지되도록 하는 로직 필요
    private int quantity;

}