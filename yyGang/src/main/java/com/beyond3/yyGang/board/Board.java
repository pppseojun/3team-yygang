package com.beyond3.yyGang.board;

import com.beyond3.yyGang.Answer;
import com.beyond3.yyGang.Comment;
import com.beyond3.yyGang.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "board_title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String boardContents;

    @CreationTimestamp // Insert 발생 -> 현재 시간으로 값 채워서 쿼리 생성
    private LocalDateTime boardDate; // 게시글 작성 날짜

    @UpdateTimestamp // Update 시 자동으로 값을 채워줌
    private LocalDateTime boardMdate; // 게시글 수정 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "board")
//    private List<Comment> comments;
//
//    @OneToMany(mappedBy = "qboard")
//    private List<Answer> answers;


}
