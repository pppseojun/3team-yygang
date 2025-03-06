package com.beyond3.yyGang.user.domain;

import com.beyond3.yyGang.handler.exception.UserException;
import com.beyond3.yyGang.handler.message.UserExceptionMessage;
import com.beyond3.yyGang.review.Review;
import com.beyond3.yyGang.user.dto.UserModifyDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "user")
@AllArgsConstructor // 모든 필드 값이 있는 경우는 생성자로 생성하도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 자동 추가 막음
@Builder
public class User implements UserDetails {

    // 기본키 userId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId; // 회원 ID

    // CUSTOMER, SELLER, PHARMACIST, ADMIN -> default : CUSTOMER
    @Enumerated(EnumType.STRING)
    private Role_name role;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", nullable = false)
////    private Role role; // 역할 매핑, 기본 값은 CUSTOMER

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

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    public User(Role_name role, String email, String password, String name, Gender gender) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public void updateUserInfo(UserModifyDto dto) {
        Optional.ofNullable(dto.getName()).ifPresent(this::setName);
        Optional.ofNullable(dto.getRole()).ifPresent(
                role -> {
                    if(role.equals(Role_name.ADMIN)) {
                        throw new UserException(UserExceptionMessage.CANNOT_SELECT_ADMIN);
                    }
                    this.role = role;
                }
        );
        Optional.ofNullable(dto.getAge()).ifPresent(this::setAge);
        Optional.ofNullable(dto.getGender()).ifPresent(this::setGender);
        Optional.ofNullable(dto.getPhone()).ifPresent(this::setPhone);
        Optional.ofNullable(dto.getAddress()).ifPresent(this::setAddress);
    }


    @Override
    // 사용자 권한을 객체로 반환하는 기능을 수행함 -> ROLE_ADMIN, ROLE_SELLER ...
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + role.toString());

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /*===================================================================*/

    /*===================================================================*/
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
