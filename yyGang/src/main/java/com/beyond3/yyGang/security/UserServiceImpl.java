package com.beyond3.yyGang.security;

import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

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
