package com.beyond3.yyGang.answer.repository;


import com.beyond3.yyGang.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByAnswerIdAndQboard_QboardId(Long answerId, Long questionId);
}
