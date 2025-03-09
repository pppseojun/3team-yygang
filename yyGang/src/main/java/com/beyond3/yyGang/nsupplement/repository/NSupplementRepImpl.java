package com.beyond3.yyGang.nsupplement.repository;

import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.dto.NSupplementResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class NSupplementRepImpl implements NSupplementRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public NSupplementRepImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<NSupplementResponseDto> search(Pageable pageable) {
        return null;
    }

    // 영양제 이름으로 검색




    // 영양제 성분 별 검색

    // 영양제 카테고리 별 검색

}
