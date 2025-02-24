package com.beyond3.yyGang.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    // JWT Token을 필터링, 유효한 토큰인 경우 사용자 인증 정보를 SecurityContext에 저장
    // UsernamePasswordAuthenticationFilter 이전이 실행되는 내용

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = resolveToken((HttpServletRequest) request);

        if(token!= null && jwtTokenProvider.validateToken(token)) {
            // token이 유효하고, null이 아니라면
            // Token에서 Authentication 객체를 가지고 와서 SecurityContext에 저장함
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }


    // HTTP 요청에서 Token을 추출하는 과정
    private String resolveToken(HttpServletRequest request) {

        // request의 Authorization Header에서 bearerToken을 추출하는 과정
        String bearerToken = request.getHeader("Authorization");

        // bearerTokeb의 Header가 존재하고, Bearer 로 시작하는지 확인
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            // 앞의 Bearer 접두사 제거, 실제 토큰만 반환
            return bearerToken.substring(7, bearerToken.length());
        }

        // BearerToken 없으면 Null 반환
        return null;
    }
}
