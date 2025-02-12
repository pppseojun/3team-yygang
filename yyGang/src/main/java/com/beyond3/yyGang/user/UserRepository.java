package com.beyond3.yyGang.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    // 생성자를 통한 의존성 주입
    private final EntityManager em;

    // 회원 가입
    public void save(User user){
        em.persist(user);
        em.flush();
    }

    // 회원 단건 조회
    public User findById(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }


}
