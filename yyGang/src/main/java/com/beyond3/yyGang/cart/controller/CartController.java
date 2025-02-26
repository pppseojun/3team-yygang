package com.beyond3.yyGang.cart.controller;

import com.beyond3.yyGang.cart.Cart;
import com.beyond3.yyGang.cart.CartOption;
import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.cart.service.CartService;
import com.beyond3.yyGang.security.JwtTokenProvider;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartOptionRepository cartOptionRepository;

    // 장바구니에 담겨 있는 값들 확인
    @GetMapping("/")
    public ResponseEntity<List<CartOption>> getCart(@RequestHeader("Authorization") String token) {

        List<CartOption> cartList = cartService.getCartListFormToken(token);
        return ResponseEntity.ok(cartList);
    }

}
