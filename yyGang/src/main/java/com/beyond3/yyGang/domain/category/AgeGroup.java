package com.beyond3.yyGang.domain.category;

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
@Table(name = "age_group")
public class AgeGroup {
    @Id
    @GeneratedValue
    @Column(name = "age_ID")
    private Long ageID;

    @Column(name = "age_group")
    private String ageGroup;

    @OneToMany(mappedBy = "ageGroup")
    private List<ACategory> aCategories;
}
