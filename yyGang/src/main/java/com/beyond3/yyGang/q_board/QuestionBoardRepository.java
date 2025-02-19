package com.beyond3.yyGang.q_board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class QuestionBoardRepository  {

    @PersistenceContext
    private EntityManager em;

    // 저장하기
    public void save(QuestionBoard questionBoard) {
        em.persist(questionBoard);
    }

    // 모든 게시글 조회

    public List<QuestionBoard> findAll() {
        return em.createQuery("SELECT q FROM QuestionBoard q", QuestionBoard.class).getResultList();
    }

    // 특정 ID로 게시글 조회
    public QuestionBoard findById(Long qboardId) {
        return em.find(QuestionBoard.class,qboardId);
    }


}
