package com.beyond3.yyGang.cart.controller;

import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.cart.service.CartService;
import com.beyond3.yyGang.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartOptionRepository cartOptionRepository;

    // 장바구니에 담겨 있는 값들 확인
    private final JwtTokenProvider jwtTokenProvider;


    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Long> deleteCartOption(@PathVariable("cartId") Long cartId,
                                                 @RequestHeader("Authorization") String token) {
        log.info("Received Authorization Header: {}", token);  // Authorization 헤더 로그 출력


        String email = getUserEmailFromToken(token);
        log.info("Extracted Email from Token: {}", email);  // 토큰에서 추출된 이메일 확인

        cartService.deleteCartOption(cartId);
        return ResponseEntity.ok(cartId);
    }


    // Token 정보에서 Email 추출하기
    private String getUserEmailFromToken(String token) {
        String trimToken = token.substring(7).trim();

        if (!jwtTokenProvider.validateToken(trimToken)) {
            // 토큰이 유효하지 않은 경우
            throw new UsernameNotFoundException("유효하지 않은 토큰입니다.");
        }
        return jwtTokenProvider.getAuthentication(trimToken).getName();
    }

}
