package com.beyond3.yyGang.pay.controller;

import com.beyond3.yyGang.order.dto.OrderResultDto;
import com.beyond3.yyGang.pay.dto.PaymentDto;
import com.beyond3.yyGang.pay.dto.PaymentResultDto;
import com.beyond3.yyGang.pay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/")
    public ResponseEntity<PaymentResultDto> processPayment(
            @RequestHeader("Authorization") String token,
            @RequestBody PaymentDto paymentDto,
            OrderResultDto orderResultDto) {

        PaymentResultDto paymentResultDto = paymentService.paymentProcess(token, paymentDto, orderResultDto);
        log.info("Payment process result: {}", paymentResultDto);

        return ResponseEntity.ok().build();
    }
}
