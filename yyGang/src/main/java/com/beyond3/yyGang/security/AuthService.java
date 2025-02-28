package com.beyond3.yyGang.security;

public interface AuthService {

    JwtToken signIn(String username, String password);
    JwtToken refresh(String token);
    void logout(String token);

}
