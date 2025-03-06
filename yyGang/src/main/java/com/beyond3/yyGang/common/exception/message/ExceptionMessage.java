package com.beyond3.yyGang.common.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    NOT_FOUND_USER("회원을 찾을 수 없습니다.",HttpStatus.NOT_FOUND),
    EMPTY_CONTENT("작성하신 내용이 없습니다.",HttpStatus.BAD_REQUEST),
    EMPTY_TITLE("제목을 작성하세요",HttpStatus.BAD_REQUEST),

    NOT_FOUND_QUESTION_BOARD("질문 게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    NOT_FOUND_ANSWER("답글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    BAD_REQUEST_ANSWER("사용자 이름 혹은 게시글이 없습니다.", HttpStatus.BAD_REQUEST),


    // 이거 Conflit쓰는거 맞는지
    ALREADY_PRESSED("좋아요 이미 눌렀습니다.",HttpStatus.CONFLICT),
    NOT_FOUND_ANSWER_LIKE("좋아요를 누른적이 없습니다.",HttpStatus.CONFLICT),

    INVAILD_VALUE("값을 정수로 입력해주세요",HttpStatus.BAD_REQUEST),

    NOT_FOUND_SUPPLEMENT("상품을 찾을 수 없습니다.", HttpStatus.NOT_FOUND)

    ;

    private final String message;
    private final HttpStatus status;
}
