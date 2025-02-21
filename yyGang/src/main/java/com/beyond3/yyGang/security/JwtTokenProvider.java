package com.beyond3.yyGang.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component  // 빈으로 등록할 예정
public class JwtTokenProvider {
    private final Key key;  // JWT 서명에 사용될 비밀 키

    // Secret Key의 초기화 부분
    public JwtTokenProvider(
            // application.properties에서 설정한 비밀키를 가져옴
            @Value("${jwt.secret.key}") String secretKey
    ) {
        // Secret Key를 Base64로 디코딩 -> 바이트 배열로 변환
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // HMAC-SHA 알고리즘을 사용할 수 있는 서명용 비밀 키로 변환, this.key에 저장
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // User 정보를 바탕으로 Access Token, Refresh Token 생성
    public JwtToken generateToken(Authentication authentication) {

        // 권한 가져오기 -> 이후 AccessToken에 넣을 예정
        String auths = authentication       // 현재 로그인한 사용자의 인증 정보를 담은 객체
                .getAuthorities().stream()  // 사용자의 권한 목록을 반환 -> stream으로 변환
                .map(GrantedAuthority::getAuthority)    // 실제 권한 문자열을 추출
                .collect(Collectors.joining(","));  // 각 권한을 쉼표로 구분된 문자열로 반환

        long now = (new Date()).getTime();   // 현재 시간

        Date accessTokenExp = new Date(now + 3600 * 1000);

        // AccessToken 생성
        String accessToken = Jwts.builder()
                .subject(authentication.getName())  // 토큰 사용자 이름으로 설정
                .claim("auths", auths)           // 사용자 권한을 "auths"라는 클레임으로 추가
                .expiration(accessTokenExp)         // AccessToken 만료 기간 설정
                .signWith(SignatureAlgorithm.HS512, key)    // 토큰에 서명하기 -> Hs512 알고리즘, key를 이용해서 서명
                .compact();

        // RefreshToken 생성
        String refreshToken = Jwts.builder()
                .expiration(new Date(now + 86400000))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        // accessToken + refreshToken => JWT Token 생성, 반환
        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT 토큰을 복호화해서 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken); // claims로 parsing

        if(claims.get("auths") != null) {
            throw new RuntimeException("권한 정보가 없는 Token입니다.");
        }

        // "auths" 라는 이름으로 만들어진 권한 claim을 꺼내서 권한 collection 리스트로 반환
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)   // 각 권한을 SimpleGrantedAuthoruty 객체로 변환
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 반환
        // UserDetails : interface
        // User : UserDetails를 구현한 클래스
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }


    // 토큰 정보 검증 메서드
    public boolean validateToken(String token) {
        try{
            Jwts.parser()      // jwt parser 객체 생성 - JWT 토큰 분석, 안에 포함된 데이터 추출
                    .setSigningKey(key)   // 서명 검증에 사용할 비밀 key 설정 -> JWT 토큰 생성 시 사용한 key와 일치해야 함
                    .build()
                    .parseClaimsJws(token);  // jwt 토큰을 parsing 하여 클레임을 추출
            return true;  // 토큰이 유효할 경우 true 반환
        } catch (SecurityException | MalformedJwtException e){
            log.info("Invalid JWT token", e);  // 유효하지 않은 토큰
        } catch (ExpiredJwtException e){
            log.info("Expired JWT token", e);   // 만료된 토큰
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT token", e);  // 지원하지 않는 토큰
        } catch (IllegalArgumentException e){
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    // JWT 토큰에서 클레임(Claim)을 추출하는 메서드
    private Claims parseClaims(String accessToken) {
        try {
            return  Jwts.parser()   // JWT Parser를 생성
                    .verifyWith((SecretKey) key) // 서명 검증에 사용할 비밀 키 설정
                    .build()
                    .parseSignedClaims(accessToken) // JWT 토큰을 파싱해 claim 생성
                    .getBody();     // claim 반환
        } catch (ExpiredJwtException e) {
            return e.getClaims();   // 만료된 토큰일 경우 예외에서 claim을 반환
        }
    }
}
