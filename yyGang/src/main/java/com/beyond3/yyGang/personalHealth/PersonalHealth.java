package com.beyond3.yyGang.personalHealth;

import com.beyond3.yyGang.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "personal_health")
public class PersonalHealth {

    @Id
    @GeneratedValue
    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "sur_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime surDate; // 설문 완료 시간

    @Column(name = "sur_complete", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean surComplete;  // 설문 완료 여부

    private String contents;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
