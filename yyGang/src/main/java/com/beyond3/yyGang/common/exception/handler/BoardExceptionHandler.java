package com.beyond3.yyGang.common.exception.handler;

import com.beyond3.yyGang.common.AnswerLikeException;
import com.beyond3.yyGang.common.QuestionBoardException;
import com.beyond3.yyGang.common.AnswerException;
import com.beyond3.yyGang.common.exception.dto.ApiErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BoardExceptionHandler {
    @ExceptionHandler(QuestionBoardException.class)
    public ResponseEntity<ApiErrorResponseDto> handleQuestionBoardException(QuestionBoardException e) {
        log.error("QboardException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ApiErrorResponseDto(e.getStatus().value(),e.getType(),e.getMessage()),
                e.getStatus()
        );

    }

    @ExceptionHandler(AnswerException.class)
    public ResponseEntity<ApiErrorResponseDto> handleQuestionBoardException(AnswerException e) {
        log.error("AnswerException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ApiErrorResponseDto(e.getStatus().value(),e.getType(),e.getMessage()),
                e.getStatus()
        );
    }

    @ExceptionHandler(AnswerLikeException.class)
    public ResponseEntity<ApiErrorResponseDto> handleQuestionBoardException(AnswerLikeException e) {
        log.error("AnswerLikeException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ApiErrorResponseDto(e.getStatus().value(),e.getType(),e.getMessage()),
                e.getStatus()
        );
    }




}
