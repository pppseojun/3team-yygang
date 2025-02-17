package com.beyond3.yyGang.q_board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {

    List<QuestionBoard> findByQuestionId(Long qboardId);

    List<QuestionBoard> findByUserId(Long userId);


}
