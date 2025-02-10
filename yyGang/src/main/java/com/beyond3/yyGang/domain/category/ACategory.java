package com.beyond3.yyGang.domain.category;

import com.beyond3.yyGang.domain.store.NSupplements;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "a_category")
public class ACategory {

    @Id
    @GeneratedValue
    @Column(name = "Key")
    private Long key;

    @ManyToOne
    @JoinColumn(name = "products_ID")
    private NSupplements nSupplements;

    @ManyToOne
    @JoinColumn(name = "age_ID")
    private AgeGroup ageGroup;


}
