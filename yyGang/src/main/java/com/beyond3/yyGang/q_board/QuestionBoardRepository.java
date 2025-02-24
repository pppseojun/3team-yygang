package com.beyond3.yyGang.q_board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Repository
//@Transactional
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Integer> {

}
