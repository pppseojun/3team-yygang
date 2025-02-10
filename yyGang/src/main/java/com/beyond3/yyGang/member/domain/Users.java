package com.beyond3.yyGang.member.domain;

import com.beyond3.yyGang.board.domain.Board;
import com.beyond3.yyGang.board.domain.Comments;
import com.beyond3.yyGang.board.domain.NAnswer;
import com.beyond3.yyGang.board.domain.NQuestion;
import com.beyond3.yyGang.board.domain.QuestionBoard;
import com.beyond3.yyGang.board.domain.Review;
import com.beyond3.yyGang.store.domain.Cart;
import com.beyond3.yyGang.store.domain.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class Users {

    // 기본키 userId
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long userId; // 회원 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role; // 역할 매핑

    @Column(unique = true) // 이메일은 중복되어선 안됨
    private String email;

    private String password;

    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;  // 성별 -> MALE, FEMALE

    private String phone; // 전화번호

    @Column(name = "created_date")
    private LocalDateTime createdDate;  // 가입 일자

    private String address; // 주소

    @OneToMany(mappedBy = "users")
    private List<Review> reviews;

    @OneToMany(mappedBy = "users")
    private List<QuestionBoard> questionBoards;

    @OneToOne(mappedBy = "users")
    private PersonalHealth personalHealth;

    @OneToMany(mappedBy = "users")
    private List<Order> orders;

    @OneToMany(mappedBy = "users")
    private List<NQuestion> nQuestions;

    @OneToMany(mappedBy = "users")
    private List<NAnswer> nAnswers;

    @OneToMany(mappedBy = "users")
    private List<Board> boards;

    @OneToMany(mappedBy = "users")
    private List<Comments> comments;

    @OneToOne(mappedBy = "user")
    private Cart cart;

}
