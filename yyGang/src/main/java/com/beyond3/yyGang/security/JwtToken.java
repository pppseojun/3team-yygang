package com.beyond3.yyGang.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {

    // 클라이언트에 토큰을 보내기 위한 DTO
    private String grantType;       // 인증 타입
    private String accessToken;     // accessToken
    private String refreshToken;    // refreshToken
}
