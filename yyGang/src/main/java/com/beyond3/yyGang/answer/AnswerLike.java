package com.beyond3.yyGang.answer;

import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "answer_like")
public class AnswerLike {

    @EmbeddedId
    private AnswerLikedId id;


    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @MapsId("answerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answerId;

}
