package com.beyond3.yyGang.cart.repository;

import com.beyond3.yyGang.cart.Cart;
import com.beyond3.yyGang.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // 페치 조인 사용 고려, 아마 사용해야할듯
    @Query("select c from Cart c where c.user.userId = :userId")
    Optional<Cart> findByUserId(@Param("userId") Long userId);

}