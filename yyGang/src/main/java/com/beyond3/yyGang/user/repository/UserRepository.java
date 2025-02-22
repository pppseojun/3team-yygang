package com.beyond3.yyGang.user.repository;

import com.beyond3.yyGang.user.domain.User;
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
    }

    // 회원 아이디 - 단건 조회
    public User findById(Long id){
        return em.find(User.class, id);
    }

    // 회원 이메일 조회
    public User findByEmail(String email){
        // 이메일은 unique한 값 -> 값이 이미 있으면 return
        List<User> result = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    // 전체 회원 조회 -> 관리자 기능
    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    // 스프링 데이터 JPA 쓰자...
    // JPA Pageable -> 페이징 처리

    // 메소드는 한 가지 일만 하도록 하는 것이 좋다.
    // 가변 인자를 받아 처리하는 것도 괜찮기는 하나, -> 반환 등의 문제 발생
    //
}
