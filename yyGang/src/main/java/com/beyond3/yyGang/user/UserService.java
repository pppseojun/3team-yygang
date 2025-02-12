package com.beyond3.yyGang.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    // 생성자를 통한 의존성 주입
    private final UserRepository userRepository;

    @Transactional(readOnly = false)
    // 회원 가입 -> 가입 후 회원ID가 반환되도록
    public Long join(User user){
        // Member 중복 조회
        validateMember(user);
        userRepository.save(user);

        return user.getUserId();
    }

    // 이미 존재하는 회원인지 확인
    private void validateMember(User user){
        User tmpUser = userRepository.findById(user.getUserId());
        if(tmpUser != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


}
