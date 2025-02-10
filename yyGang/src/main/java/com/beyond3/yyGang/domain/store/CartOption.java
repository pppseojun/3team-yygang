package com.beyond3.yyGang.domain.store;

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
@Table(name = "cart_option")
public class CartOption {
    @Id
    @GeneratedValue
    @Column(name = "cart_option_ID")
    private Long cartOptionId;

    private int quantity; // 수량

    private int price;  // 가격

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_ID")
    private NSupplements nSupplements;



}
