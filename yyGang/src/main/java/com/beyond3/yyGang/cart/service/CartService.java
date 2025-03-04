package com.beyond3.yyGang.cart.service;

import com.beyond3.yyGang.cart.Cart;
import com.beyond3.yyGang.cart.CartOption;
import com.beyond3.yyGang.cart.repository.CartOptionRepository;
import com.beyond3.yyGang.cart.repository.CartRepository;
import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nsupplement.repository.NSupplementRepository;
import com.beyond3.yyGang.auth.JwtTokenProvider;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartOptionRepository cartOptionRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final NSupplementRepository nSupplementRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    // 사용자가 장바구니에 영양제 추가 기능
    public Long saveCartOption(Long userId, Long nSupplementId, int quantity) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found for userId"));   // 사용자 확인 메서드

        NSupplement findNSupplement = nSupplementRepository.findById(nSupplementId)
                .orElseThrow(() -> new EntityNotFoundException("NSupplement not found for nSupplementId")); // 해당 영양제 확인

        Cart findCart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for userId"));   // 장바구니 있는지 확인

        CartOption cartOption = CartOption.createCartOption(findCart, findNSupplement, quantity);
        // 새로운 cartoption 객체 생성

        CartOption saveCartOption = cartOptionRepository.save(cartOption);
        // cartOption 객체 저장

        return saveCartOption.getCartOptionID();
    }

    // void와 Long중 어느 거 사용, user를 파라미터로 받아와야 하는지???
    // List
    // 장바구니에서 선택한 아이템 삭제 기능
    @Transactional
    public void deleteCartOption(Long cartOptionId/*, Long userId*/) {
        // User findUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found for userId"));
        CartOption findCartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(() -> new EntityNotFoundException("CartOption not found for CartOptionId"));
        //Cart findCart = cartRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("Cart not found for userId"));

        cartOptionRepository.delete(findCartOption);
    }

    @Transactional
    // 장바구니 수량 증가 기능
    public void increaseCartOptionQuantity(Long cartOptionId, int quantity) {
        CartOption findCartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(() -> new EntityNotFoundException("CartOption not found for CartOptionId"));
        findCartOption.increaseQuantity(quantity);
    }

    @Transactional
    // 장바구니 수량 감소 기능
    public void decreaseCartOptionQuantity(Long cartOptionId, int quantity) {
        CartOption findCartOption = cartOptionRepository.findById(cartOptionId).orElseThrow(() -> new EntityNotFoundException("CartOption not found for CartOptionId"));
        findCartOption.decreaseQuantity(quantity);
    }

    @Transactional
    public List<CartOption> getCartListFormToken(String token){
        String userEmailFromToken = getUserEmailFromToken(token);   // 토큰을 받아 사용자 이메일 반환
        User user = getUserFromEmail(userEmailFromToken);   // 사용자 이메일로 사용자 찾기

        Long cartId = cartRepository.findByUserId(user.getUserId()).get().getCartId();  // 사용자에게서 id를 받아서 cartId 조회
        List<CartOption> cartList = cartOptionRepository.findByCartId(cartId);  // 해당 cartId를 갖는 CartOption 리스트를 조회
        return cartList;
    }

    // Token 정보에서 Email 추출하기
    private String getUserEmailFromToken(String token){
        String trimToken = token.substring(7).trim();

        if(!jwtTokenProvider.validateToken(trimToken)){
            // 토큰이 유효하지 않은 경우
            throw new UsernameNotFoundException("유효하지 않은 토큰입니다.");
        }
        return jwtTokenProvider.getAuthentication(trimToken).getName();
    }

    private User getUserFromEmail(String userEmail){
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if(optionalUser.isEmpty()){
            // 존재하지 않는 이메일이면?
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return optionalUser.get();
    }


}