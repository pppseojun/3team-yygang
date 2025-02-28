package com.beyond3.yyGang.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getStatus().value(),
                        e.getType(),
                        e.getMessage()),
                    e.getStatus()
                );
    }
}
