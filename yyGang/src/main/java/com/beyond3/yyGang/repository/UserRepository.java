package com.beyond3.yyGang.repository;

import com.beyond3.yyGang.domain.member.Users;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor //@Autowired 생략 가능
public class UserRepository {

    private final EntityManager em;

    // 회원 저장 -> 회원 가입
    public void save(Users users){
        em.persist(users);
    }

    // 회원 단건 조화
    public Users findByUserId(Long userId){
        return em.find(Users.class, userId);
    }

}
