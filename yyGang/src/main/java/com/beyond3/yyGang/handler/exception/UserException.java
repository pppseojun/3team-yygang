package com.beyond3.yyGang.handler.exception;

import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class UserException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3759376407949162781L;

    private final String type;
    private final HttpStatus status;

    public UserException(UserExceptionMessage message) {
        super(message.getMessage());
        this.type = message.name();
        this.status = message.getStatus();
    }
}
