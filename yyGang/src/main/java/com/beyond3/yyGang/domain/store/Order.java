package com.beyond3.yyGang.domain.store;

import com.beyond3.yyGang.domain.member.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "order")
public class Order {
    //주문

    @Id
    @GeneratedValue
    @Column(name = "order_ID")
    private Long orderID;  // 주문 고유 ID

    // 한 회원이 여러 개의 주문을 생성 가능 -> 주문 입장에서 회원은 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ID")
    private Users users;

    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderOption> orderOptions;

}
