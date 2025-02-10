package com.beyond3.yyGang.domain.category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue
    @Column(name = "ingredient_ID")
    private Long ingredientID; // 영양 성분 ID

    private String ingredient; // 영양 성분
}
