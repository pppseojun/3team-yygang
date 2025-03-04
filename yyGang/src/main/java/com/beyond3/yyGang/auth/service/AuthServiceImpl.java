package com.beyond3.yyGang.auth.service;

import com.beyond3.yyGang.auth.dto.JwtToken;
import com.beyond3.yyGang.auth.JwtTokenProvider;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional
    public JwtToken signIn(String username, String password) {
        // 사용자 이메일, 비밀번호를 입력 받아서 인증 토큰 생성
        Optional<User> user = userRepository.findByEmail(username);

        if(user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            // 사용자가 없거나, 패스워드가 일치하지 않음
            throw new UserException(UserExceptionMessage.INVALID_CREDENTIALS);
        }

        // 위의 과정을 통과할 경우 -> ㅇㅇ
        return new JwtToken(
                jwtTokenProvider.createAccessToken(user.get().getEmail(), user.get().getRole()),
                jwtTokenProvider.createRefreshToken(user.get().getEmail())
        );
    }

    @Override
    public void logout(String token){
        // token에서 access Token만 추출
        String accessToken = jwtTokenProvider.resolveToken(token);

        // 추출한 토큰 유효성 확인
        jwtTokenProvider.validateToken(accessToken);

        // access Token을 BlackList에 담고
        jwtTokenProvider.addBlackList(accessToken);

        // RefreshToken은 삭제하면 됨.
        jwtTokenProvider.deleteRefreshToken(accessToken);
    }


    // 토큰 Refresh 토큰 전략을 어떻게 해야 하지? -> 만료되기 1분 정도 전에 갱신할건지 물어볼 때인가?
    @Override
    public JwtToken refresh(String token) {

        // 토큰 추출 -> 앞 쪽의 Bearer 제외시키기
        String bearerToken = jwtTokenProvider.resolveToken(token);

        // 해당 토큰이 유효한지 확인
        jwtTokenProvider.validateToken(bearerToken);

        User user = userRepository.findByEmail(jwtTokenProvider.getUserName(bearerToken))
                .orElseThrow(() -> new UserException(UserExceptionMessage.USER_NOT_FOUND));

        // Redis에서 Refresh 토큰 있는지 여부 검사
        if(!jwtTokenProvider.isValidRefreshToken(bearerToken)) {
            throw new UserException(UserExceptionMessage.INVALID_ACCESS_TOKEN);
        }

        // Refresh는 이전에 쓰던걸 그대로 유지하는 편이 나음
        return new JwtToken(
                jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole()),
                redisTemplate.opsForValue().get("refreshToken:" + user.getEmail())
        );
    }


}
