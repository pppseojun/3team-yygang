package com.beyond3.yyGang.ageCategory;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.q_board.QuestionBoard;
import com.beyond3.yyGang.user.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "a_item")
public class AgeGroup {
    @Id
    @GeneratedValue
    @Column(name = "age_id")
    private Long ageId;

    @Column(name = "age_name")
    private String ageName;

    @OneToMany(mappedBy = "ageGroup")
    private List<ACategory> aCategories;

    @Entity
    @Getter
    @Table(name = "comment")
    public static class Comments {

        @Id
        @GeneratedValue
        @Column(name = "comment_id")
        private Long commentId;

        @Column(columnDefinition = "TEXT")
        private String commentContents;

        private LocalDateTime commentDate;

        private LocalDateTime commentMdate;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private Users users;

        @ManyToOne
        @JoinColumn(name = "board_id")
        private Board board;
    }

    @Entity
    @Getter
    @Table(name = "answer")
    public static class Answer {

        @Id
        @GeneratedValue
        @Column(name = "answer_id")
        private Long answerId;

        @Column(columnDefinition = "TEXT")
        private String answerContents;

        private LocalDateTime answerDate;

        private LocalDateTime answerMdate;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private Users users;

        @ManyToOne
        @JoinColumn(name = "qboard_id")
        private QuestionBoard qboard;
    }
}
