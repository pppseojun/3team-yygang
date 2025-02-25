package com.beyond3.yyGang.q_board.repository;

import com.beyond3.yyGang.q_board.entity.QuestionBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional
public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {
    Page<QuestionBoard> findAll(Pageable pageable);
}
