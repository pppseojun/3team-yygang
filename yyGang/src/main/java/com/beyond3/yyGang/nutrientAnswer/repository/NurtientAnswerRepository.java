package com.beyond3.yyGang.nutrientAnswer.repository;

import com.beyond3.yyGang.nutrientAnswer.NAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurtientAnswerRepository extends JpaRepository<NAnswer, Long> {
}
