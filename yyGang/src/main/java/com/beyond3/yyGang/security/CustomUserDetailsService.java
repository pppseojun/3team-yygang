package com.beyond3.yyGang.security;

import com.beyond3.yyGang.security.dto.CustomUserDetails;
import com.beyond3.yyGang.user.domain.User;
import com.beyond3.yyGang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. ");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(user.getRole().toString())
                .build();
    }
}
