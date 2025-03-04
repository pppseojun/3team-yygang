package com.beyond3.yyGang.order.repository;

import com.beyond3.yyGang.order.Order;
import com.beyond3.yyGang.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderID(Long orderId);
}