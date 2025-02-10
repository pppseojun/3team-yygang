package com.beyond3.yyGang.domain.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "payment_ID")
    private Long paymentID; // 결제 ID

    private Long totalPrice; // 전체 가격

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PayStatus paymentStatus;  // 결제 상태 - WAITING, FAIL, SUCCESS

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime date; // 결제 날짜

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;  // 주문 아이디 참조




}
