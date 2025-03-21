package com.beyond3.yyGang.board.repository;

import com.beyond3.yyGang.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    Page<Board> findAll(Pageable pageable);

}
