package com.beyond3.yyGang.ageCategory;

import com.beyond3.yyGang.nsupplement.NSupplements;
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
    @Column(name = "a_category_id")
    private Long aCategoryId;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private NSupplements nSupplements;

    @ManyToOne
    @JoinColumn(name = "age_id")
    private AgeGroup ageGroup;


}
