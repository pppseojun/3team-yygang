package com.beyond3.yyGang.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/yyGang/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    // 메인 페이지
    @GetMapping("/")
    public String index(){
        return "/index"; // 뷰 이름 반환
    }

    // 회원 가입 페이지 -> Get 방식으로 페이지 보기
    @GetMapping("/user/join")
    public String yyJoin(){
        return "/join";
    }

    // 로그인 페이지 -> Get 방식으로 페이지 보기
    @GetMapping("/user/login")
    public String yyLogin(){
        return "/login";
    }

    // 회원 가입 처리 -> Post 방식
    @PostMapping("/join")
    public String join(@RequestBody User user, Model model) {
        userService.join(user);
        model.addAttribute("user", user);
        return "redirect:/user/login";     // 회원 가입 완료 후 로그인 화면으로 이동
    }

}
