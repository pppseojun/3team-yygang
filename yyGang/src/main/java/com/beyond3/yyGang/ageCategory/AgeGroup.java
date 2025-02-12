package com.beyond3.yyGang.ageCategory;

import com.beyond3.yyGang.board.Board;
import com.beyond3.yyGang.q_board.QuestionBoard;
import com.beyond3.yyGang.user.User;
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

//    @OneToMany(mappedBy = "ageGroup")
//    private List<ACategory> aCategories;
}
