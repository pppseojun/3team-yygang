package com.beyond3.yyGang.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user")
@AllArgsConstructor // 모든 필드 값이 있는 경우는 생성자로 생성하도록
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 자동 추가 막음
public class User {

    // 기본키 userId
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId; // 회원 ID

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id", nullable = false)
////    private Role role; // 역할 매핑, 기본 값은 CUSTOMER

    @Column(nullable = false, unique = true) // 이메일은 중복되어선 안됨, 값이 필수로 있어야 함
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;  // 성별 -> MALE, FEMALE

    private String phone; // 전화번호

    @CreationTimestamp
    // 최초 가입 일자 -> Insert 쿼리 발생 시 현재 시간을 값으로 채워서 쿼리 생성
    private LocalDateTime createdDate;  // 가입 일자

    private String address; // 주소

    @Builder
    public User(Integer age, Gender gender, String phone, LocalDateTime createdDate, String address) {
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.createdDate = createdDate;
        this.address = address;
    }

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
