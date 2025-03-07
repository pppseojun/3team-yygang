package com.beyond3.yyGang.nutrientQuestion;

import com.beyond3.yyGang.nsupplement.NSupplement;
import com.beyond3.yyGang.nutrientAnswer.NAnswer;
import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "n_question")
public class NutrientQuestion {
    // 영양제 관련 문의사항

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId; // 문의 사항 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private NSupplement supplement;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createAt; // 질문 작성 날짜

    @Column(columnDefinition = "TEXT")
    private String qContent; // 질문 내용

    @OneToOne(mappedBy = "question")
    private NAnswer nAnswer;

    @Builder
    public NutrientQuestion(User user, NSupplement supplement, LocalDateTime createAt, String qContent,Long questionId) {
        this.user = user;
        this.supplement = supplement;
        this.createAt = createAt;
        this.qContent = qContent;
        this.questionId = questionId;
    }

    public void update(String questionContent) {
        this.qContent = questionContent;
    }
}
