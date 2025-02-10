package com.beyond3.yyGang.domain.board;

import com.beyond3.yyGang.domain.member.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Comment")
public class Comments {

    @Id
    @GeneratedValue
    @Column(name = "comment_ID")
    private Long commentId;

    private String commentType;

    private String commentContents;

    private LocalDateTime commentDate;
    private LocalDateTime commentMdate;

    @ManyToOne
    @JoinColumn(name = "c_userID")
    private Users users;
}
