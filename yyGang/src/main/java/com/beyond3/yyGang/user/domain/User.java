package com.beyond3.yyGang.user.domain;

import com.beyond3.yyGang.user.dto.UserInfoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor // 모든 필드 값이 있는 경우는 생성자로 생성하도록
@NoArgsConstructor(access = AccessLevel.PUBLIC)  // 기본 생성자 자동 추가 막음
@Builder
public class User {

    // 기본키 userId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId; // 회원 ID

    // CUSTOMER, SELLER, PHARMACIST, ADMIN -> default : CUSTOMER
    @Enumerated(EnumType.STRING)
    private Role_name role;

    // 이메일은 중복되어선 안됨, 값이 필수로 있어야 함
    @Column(nullable = false, unique = true)
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
    // -> 요청 데이터가 이메일 형식을 준수하는지 검증하는데 활용
    private String email;

    @Column(nullable = false)  // 필수
    private String password;

    @Column(nullable = false)  // 필수
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)  // 필수
    private Gender gender;  // 성별 -> MALE, FEMALE

    private String phone; // 전화번호

    @CreationTimestamp
    // 최초 가입 일자 -> Insert 쿼리 발생 시 현재 시간을 값으로 채워서 쿼리 생성
    private LocalDateTime createdDate;  // 가입 일자

    private String address; // 주소

    public User(Role_name role, String email, String password, String name, Gender gender) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public UserInfoDto toUserInfoDto() {
        return UserInfoDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .role(role)
                .age(age)
                .gender(gender)
                .phone(phone)
                .address(address)
                .createdDate(createdDate)
                .build();
    }


    /*===================================================================*/

    /*===================================================================*/

//    @OneToMany(mappedBy = "users")
//    private List<Review> reviews;
//
//    @OneToMany(mappedBy = "users")
//    private List<QuestionBoard> questionBoards;
//
//    @OneToOne(mappedBy = "users")
//    private PersonalHealth personalHealth;
//
//    @OneToMany(mappedBy = "users")
//    private List<Order> orders;
//
//    @OneToMany(mappedBy = "users")
//    private List<NQuestion> nQuestions;
//
//    @OneToMany(mappedBy = "users")
//    private List<NAnswer> nAnswers;
//
//    @OneToMany(mappedBy = "users")
//    private List<Answer> answers;
//
//    @OneToMany(mappedBy = "users")
//    private List<Board> boards;
//
//    @OneToMany(mappedBy = "users")
//    private List<AgeGroup.Comments> comments;
//
//    @OneToOne(mappedBy = "user")
//    private Cart cart;

}
