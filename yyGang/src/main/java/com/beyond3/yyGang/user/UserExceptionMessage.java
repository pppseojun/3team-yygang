package com.beyond3.yyGang.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
// User Controller 예외 메시지 처리
public enum UserExceptionMessage {

    INVALID_CREDENTIALS("이메일 혹은 비밀번호가 틀립니다.", HttpStatus.UNAUTHORIZED),
    PASSWORD_NOT_MATCH("비밀번호, 비밀번호 확인이 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("이미 가입된 이메일입니다.", HttpStatus.CONFLICT),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    INVALID_REFRESH_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN("유효하지 않은 토큰입니다.", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("만료된 토큰입니다.", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_TOKEN("지원되지 않는 JWT 토큰입니다.", HttpStatus.BAD_REQUEST),
    EMPTY_TOKEN("JWT 토큰이 비어 있습니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;
}
