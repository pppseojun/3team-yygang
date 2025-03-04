package com.beyond3.yyGang.handler.exception;

import com.beyond3.yyGang.handler.message.OrderExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class OrderException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -3325163702920695888L;

  private final String type;
  private final HttpStatus status;

  public OrderException(OrderExceptionMessage message) {
      super(message.getMessage());
      this.type = message.name();
      this.status = message.getStatus();
  }
}
