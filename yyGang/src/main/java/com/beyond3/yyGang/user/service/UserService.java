package com.beyond3.yyGang.user.service;

import com.beyond3.yyGang.security.JwtToken;
import com.beyond3.yyGang.security.JwtTokenProvider;
import com.beyond3.yyGang.user.repository.UserRepository;
import com.beyond3.yyGang.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    // 생성자를 통한 의존성 주입
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    // 회원 가입 -> 가입 후 회원ID가 반환되도록
    public Long join(User user) {

        // Member 중복 조회
        validateMember(user);
        userRepository.save(user);

        return user.getUserId();
    }

    // 회원 가입 시 이미 존재하는 회원인지 확인
    public void validateMember(User user){
        // Email로 중복 확인
        User findUsers = userRepository.findByEmail(user.getEmail());
        if(findUsers != null){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    @Transactional
    public JwtToken signIn(String username, String password) {
        // 사용자 이메일, 비밀번호를 입력 받아서 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        // Authentication 객체에 사용자 이메일, 권한, 인증 정보 포함
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);

        // 인증 성공 시 위에서 만든 Authentication 객체를 바탕으로 Jwt Token을 생성해서 반환해줌
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

}
