package com.beyond3.yyGang.handler.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
// User Controller 예외 메시지 처리
public enum UserExceptionMessage {

    INVALID_CREDENTIALS("이메일 혹은 비밀번호가 틀립니다.", HttpStatus.UNAUTHORIZED),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("이미 가입된 이메일입니다.", HttpStatus.CONFLICT),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    CART_NOT_FOUND("사용자의 장바구니를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ACCOUNT_EXIST("이미 계좌가 존재합니다.", HttpStatus.CONFLICT),
    ACCOUNT_NOT_EXIST("계좌가 존재하지 않습니다.", HttpStatus.NO_CONTENT ),
    CANNOT_SELECT_ADMIN("관리자 권한을 부여할 수 없습니다.", HttpStatus.UNAUTHORIZED),

    INVALID_REFRESH_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("만료된 토큰입니다.", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_TOKEN("지원되지 않는 JWT 토큰입니다.", HttpStatus.BAD_REQUEST),
    EMPTY_TOKEN("JWT 토큰이 비어 있습니다.", HttpStatus.BAD_REQUEST),

    ONLY_SELLER_CAN_ACCESS("해당 작업에 대해 권한이 없습니다.", HttpStatus.BAD_REQUEST),
    INGREDIENT_NOT_FOUND("영양 성분에 대한 정보가 없습니다.", HttpStatus.NOT_FOUND ),
    NO_REGISTERED_PRODUCTS("등록된 상품이 없습니다.", HttpStatus.NOT_FOUND),
    HEALTH_FUNCTIONAL_ITEM_NOT_FOUND("건강 기능 항목에 대한 정보가 없습니다.", HttpStatus.NOT_FOUND),
    CANNOT_FOUND_PRODUCTS("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ALREADY_REVIEWED("이미 이 상품에 대한 리뷰를 작성했습니다.", HttpStatus.CONFLICT),
    REVIEW_NOT_FOUND("리뷰를 작성하지 않았습니다.", HttpStatus.NOT_FOUND),;

    private final String message;
    private final HttpStatus status;
}
