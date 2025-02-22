package com.beyond3.yyGang.user.dto;

import com.beyond3.yyGang.user.domain.Gender;
import com.beyond3.yyGang.user.domain.Role_name;
import com.beyond3.yyGang.user.domain.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserJoinDTO {

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String userPwd; // 비밀번호

    @NotEmpty(message = "비밀번호를 확인해주세요.")
    private String userPwd2;  // 비밀번호 확인

    @NotEmpty(message = "이름은 필수 입니다.")
    private String name;

    private LocalDateTime createdDate;  // 가입 일자

    private Integer age;

    private Gender gender;  // 성별 -> MALE, FEMALE

    private String phone; // 전화번호

    private String address; // 주소

    public User toEntity(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(userPwd);
        user.setName(name);
        user.setCreatedDate(createdDate);
        user.setAge(age);
        user.setGender(gender);
        user.setPhone(phone);
        user.setAddress(address);
        return user;
    }
}
