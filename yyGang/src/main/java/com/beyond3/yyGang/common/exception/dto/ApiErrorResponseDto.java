package com.beyond3.yyGang.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ApiErrorResponseDto {
    private final int code;
    private final String status;
    private final String message;
}
