package com.beyond3.yyGang.member.service;

import com.beyond3.yyGang.member.domain.Users;
import com.beyond3.yyGang.member.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public void join(Users users){
        userRepository.save(users);
    }


}
