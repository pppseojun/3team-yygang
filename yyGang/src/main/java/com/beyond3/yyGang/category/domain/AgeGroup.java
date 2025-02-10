package com.beyond3.yyGang.category.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

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
}
