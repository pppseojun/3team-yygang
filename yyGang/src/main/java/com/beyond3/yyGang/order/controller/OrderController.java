package com.beyond3.yyGang.order.controller;


import com.beyond3.yyGang.order.dto.OrderDto;
import com.beyond3.yyGang.order.dto.OrderResultDto;
import com.beyond3.yyGang.order.dto.RefundDto;
import com.beyond3.yyGang.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping// 상품 하나 단일 주문 -> 주문서 생성 Controller -> 이후 결제로 넘어가기
    @Operation(summary = "상품 하나 단일 주문", description = "상품 하나만 주문한다.")
    public ResponseEntity<OrderResultDto> createOrder(
            Principal principal,
            @RequestBody List<OrderDto> orderDtos) {

        String email = principal.getName();
        OrderResultDto orderResultDto = orderService.orderProduct(email, orderDtos);

        return ResponseEntity.ok(orderResultDto);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "주문 조회", description = "주문 내역을 orderID로 조회한다.")
    public ResponseEntity<OrderResultDto> orderResult(
            Principal principal,
            @PathVariable("orderId") Long orderId) {

        String email = principal.getName();
        OrderResultDto orderResultDto = orderService.getOrderResult(email, orderId);
        log.info("orderId={}", orderId);

        return ResponseEntity.ok(orderResultDto);
    }

    @PostMapping("/cart/{cartId}")// cart 주문하기
    @Operation(summary = "장바구니 주문", description = "해당 id의 장바구니에 포함된 상품 전체를 주문한다.")
    public ResponseEntity<OrderResultDto> createOrderForCart(
            Principal principal,
            @PathVariable("cartId") Long cartId) {

        String email = principal.getName();
        OrderResultDto orderResultDto = orderService.orderFromCart(email, cartId);

        return ResponseEntity.ok(orderResultDto);
    }

    @PostMapping("/{orderId}")
    @Operation(summary = "주문 취소", description = "주문 id를 받아 취소한다.")
    public ResponseEntity<RefundDto> cancelOrder(
            Principal principal,
            @PathVariable("orderId") Long orderId
    ){
        String email = principal.getName();
        RefundDto refundDto = orderService.cancelOrder(email, orderId);

        return ResponseEntity.ok(refundDto);
    }

}
