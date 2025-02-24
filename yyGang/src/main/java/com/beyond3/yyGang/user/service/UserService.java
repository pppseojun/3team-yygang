package com.beyond3.yyGang.user.service;

import com.beyond3.yyGang.security.JwtToken;
import com.beyond3.yyGang.security.JwtTokenProvider;
import com.beyond3.yyGang.user.dto.UserModifyDto;
import com.beyond3.yyGang.user.repository.UserRepository;
import com.beyond3.yyGang.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    // 생성자를 통한 의존성 주입
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    // 회원 가입 -> 가입 후 회원ID가 반환되도록
    public void join(User user) {

        // Member 중복 조회
        validateMember(user);

        String endcodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(endcodedPassword);
        userRepository.save(user);
    }

    // 회원 가입 시 이미 존재하는 회원인지 확인
    public void validateMember(User user){
        // Email로 중복 확인
        Optional<User> findUsers = userRepository.findByEmail(user.getEmail());
        if(findUsers.isPresent()){
            log.info("user : {}", findUsers);
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    @Transactional
    public JwtToken signIn(String username, String password) {
        // 사용자 이메일, 비밀번호를 입력 받아서 인증 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

        log.info("authToken: {}", authToken);
        // Authentication 객체에 사용자 이메일, 권한, 인증 정보 포함
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
        log.info("authentication: {}", authentication);

        // 인증 성공 시 위에서 만든 Authentication 객체를 바탕으로 Jwt Token을 생성해서 반환해줌
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }

    @Transactional
    public void update(User user, UserModifyDto userModifyDto) {
        // 비밀번호 외의 정보를 변경할 수 있도록
        if(userModifyDto.getName() != null){
            user.setName(userModifyDto.getName());
        }
        if(userModifyDto.getRole() != null){
            user.setRole(userModifyDto.getRole());
        }
        if(userModifyDto.getAge() != null){
            user.setAge(userModifyDto.getAge());
        }
        if(userModifyDto.getGender() != null){
            user.setGender(userModifyDto.getGender());
        }
        if(userModifyDto.getPhone() != null) {
            user.setPhone(userModifyDto.getPhone());
        }
        if(userModifyDto.getAddress() != null) {
            user.setAddress(userModifyDto.getAddress());
        }

        userRepository.save(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

}
