package com.beyond3.yyGang.hfunction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HFunctionalRepository extends JpaRepository<HFunctionalItem, Long> {

    Optional<HFunctionalItem> findByHealthName(HFunctionName hFunctionName);
}
