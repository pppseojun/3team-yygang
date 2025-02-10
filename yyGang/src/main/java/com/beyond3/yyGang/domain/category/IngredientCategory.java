package com.beyond3.yyGang.domain.category;

import com.beyond3.yyGang.domain.store.NSupplements;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "ingredient_category")
public class IngredientCategory {
    @Id
    @GeneratedValue
    @Column(name = "i_categoryID")
    private Long iCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_ID")
    private NSupplements nSupplements;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_ID")
    private Ingredient ingredient;


}
