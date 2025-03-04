package com.beyond3.yyGang.handler.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderExceptionMessage {
    ORDER_NOT_EXIST("주문이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    OUT_OF_STOCK("재고가 부족합니다.", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE("잔액이 부족합니다.", HttpStatus.BAD_REQUEST),
    NO_ITEMS_IN_CART("장바구니가 비었습니다.", HttpStatus.NOT_FOUND),
    ITEM_NOT_FOUND_IN_ORDER("주문 상품이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    CANNOT_CANCEL_ORDER("주문 취소가 불가능합니다.", HttpStatus.BAD_REQUEST),
    CANNOT_FOUND_PAY_INFO("결제 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),;

    private final String message;
    private final HttpStatus status;
}
