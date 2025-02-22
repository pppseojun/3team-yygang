package com.beyond3.yyGang.user.controller;

import com.beyond3.yyGang.security.CustomUserDetailsService;
import com.beyond3.yyGang.security.JwtToken;
import com.beyond3.yyGang.security.UserServiceImpl;
import com.beyond3.yyGang.security.dto.UserLoginDto;
import com.beyond3.yyGang.user.domain.Gender;
import com.beyond3.yyGang.user.domain.Role_name;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.dto.UserJoinDTO;
import com.beyond3.yyGang.user.repository.UserRepository;
import com.beyond3.yyGang.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 페이지 -> Get 방식으로 페이지 보기
    @GetMapping("/login")
    public String yyLogin(){
        return "user/login";
    }

    // 회원 가입 페이지 -> Get 방식으로 페이지 보기
    @GetMapping("/join")
    public String yyJoin(){
        return "user/join";
    }

    // 회원 가입 처리 -> Post 방식
    @PostMapping("/join")
    //@RequestBody의 JSON 형식으로 데이터를 넘길 예정
    public ResponseEntity<String> join(@RequestBody @Valid UserJoinDTO userJoinDTO) {

        log.info("userJoinDTO: {}", userJoinDTO);

        User user = new User();
        user.setName(userJoinDTO.getName());
        user.setEmail(userJoinDTO.getEmail());
        user.setPassword(userJoinDTO.getUserPwd());
        user.setRole("CUSTOMER");  // 기본적으로 Customer가 되도록
        user.setAddress(userJoinDTO.getAddress());

        user.builder()
                .age(userJoinDTO.getAge())
                .phone(userJoinDTO.getPhone())
                .gender(userJoinDTO.getGender())
                .build();
        userService.join(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공");
    }

    // JWT 로그인
    @PostMapping("/login")
    public JwtToken signIn(@RequestBody UserLoginDto userLoginDto){
        String userEmail = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();

        JwtToken jwtToken = userService.signIn(userEmail, password);

        log.info("request username = {}, password = {}", userEmail, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());

        return jwtToken;
    }




}
