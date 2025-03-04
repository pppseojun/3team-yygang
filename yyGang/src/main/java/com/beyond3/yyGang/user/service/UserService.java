package com.beyond3.yyGang.user.service;

import com.beyond3.yyGang.auth.dto.JwtToken;
import com.beyond3.yyGang.auth.service.AuthService;
import com.beyond3.yyGang.cart.Cart;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.user.dto.PasswordModifyDto;
import com.beyond3.yyGang.user.dto.UserInfoDto;
import com.beyond3.yyGang.user.dto.UserJoinDTO;
import com.beyond3.yyGang.user.dto.UserModifyDto;
import com.beyond3.yyGang.user.repository.UserRepository;
import com.beyond3.yyGang.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {

    // 생성자를 통한 의존성 주입
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final AuthService authService;

    @Transactional
    // 회원 가입 -> 가입 후 회원ID가 반환되도록
    public void join(UserJoinDTO userJoinDTO) {

        // Member 중복 조회
        validateMember(userJoinDTO.getEmail());

        if (!userJoinDTO.passwordMathcing()) {
            // 비밀번호, 비밀번호 확인이 일치하는지?
            throw new UserException(UserExceptionMessage.PASSWORD_NOT_MATCH);
        }

        // user 객체 생성 + 비밀번호 암호화
        User user = userJoinDTO.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 회원 가입 시 회원 당 Cart 가 자동으로 생성되도록
        Cart cart = Cart.createCart(user);
        userRepository.save(user);
        cartRepository.save(cart);
    }

    // 회원 가입 시 이메일을 토대로 이미 존재하는 회원인지 확인
    public void validateMember(String email){
        // Email로 중복 확인
        Optional<User> findUsers = userRepository.findByEmail(email);
        if(findUsers.isPresent()){
            throw new UserException(UserExceptionMessage.EMAIL_ALREADY_EXISTS);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(UserExceptionMessage.USER_NOT_FOUND));
    }

    @Transactional
    public void update(String userEmail, UserModifyDto userModifyDto) {

        User user = getUserByEmail(userEmail);

        // 비밀번호 외의 정보를 변경할 수 있도록
        user.updateUserInfo(userModifyDto);
    }

    @Transactional
    public void delete(String email, String password) {
        User user = verifyUser(email, password);

        Cart cart = cartRepository.findByUserId(user.getUserId())
                .orElseThrow(() -> new UserException(UserExceptionMessage.CART_NOT_FOUND));

        cartRepository.delete(cart);    // 장바구니 먼저 지우고
        userRepository.delete(user);    // 회원 지우기   -> cascade All로 가능?
    }

    private User verifyUser(String email, String password) {
        User user = getUserByEmail(email);

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UserException(UserExceptionMessage.PASSWORD_NOT_MATCH);
        }
        return user;
    }

    @Transactional
    public JwtToken modifyPassword(String email, PasswordModifyDto passwordModifyDto){

        User passwordModifyUser = verifyUser(email, passwordModifyDto.getOldPassword());

        passwordModifyUser.setPassword(passwordEncoder.encode(passwordModifyDto.getNewPassword()));
        userRepository.save(passwordModifyUser);

        // 새로운 토큰 등록
        return authService.signIn(passwordModifyUser.getEmail(), passwordModifyDto.getNewPassword());
    }

    @Transactional
    public List<UserInfoDto> getAllUser(){
//        return userRepository.findAll().stream()
//                .map(user -> user.toUserInfoDto())
//                .collect(Collectors.toList());
        return null;
    }

}
