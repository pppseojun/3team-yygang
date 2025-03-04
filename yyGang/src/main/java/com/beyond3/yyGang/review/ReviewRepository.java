package com.beyond3.yyGang.review;

import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUser(User user);

    @Query("SELECT r FROM Review r WHERE r.nSupplement = :nSupplement")
    List<Review> findByNSupplement(@Param("nSupplement") NSupplement nSupplement);

    @Query("SELECT r FROM Review r WHERE r.nSupplement = :nSupplement AND r.user = :user")
    Optional<Review> findByUserAndNSupplement(@Param("user") User user, @Param("nSupplement") NSupplement nSupplement);

//    Optional<Review> findByUserAndNSupplement(User user, NSupplement nSupplement);

}
