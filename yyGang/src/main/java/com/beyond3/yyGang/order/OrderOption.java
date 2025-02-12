package com.beyond3.yyGang.order;

import com.beyond3.yyGang.nsupplement.NSupplements;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "order_option")
public class OrderOption {

    @Id
    @GeneratedValue
    @Column(name = "order_option_id")
    private Long orderOptionId;  // 주문 옵션 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_ID")
    private NSupplements nSupplements;

    private int quantity;  // 수량

    private Long price;  // 가격


}
