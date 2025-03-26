package com.beyond3.yyGang.common;

import com.beyond3.yyGang.common.exception.message.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class PageException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String type;

    private final HttpStatus status;

    public PageException(ExceptionMessage message) {
      super(message.getMessage());

      this.status = message.getStatus();
      this.type = message.name();
    }

}
