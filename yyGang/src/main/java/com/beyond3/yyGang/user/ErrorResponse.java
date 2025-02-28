package com.beyond3.yyGang.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorResponse {

    private int code;
    private String status;
    private String message;
}
