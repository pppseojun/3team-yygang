package com.beyond3.yyGang.hfunction;

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
@Table(name = "h_functional_category")
public class HFunctionalCategory {

    @Id
    @GeneratedValue
    @Column(name = "hfunc_id")
    private Long hfuncId;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private NSupplements nSupplements;  // 상품ID 외래키

    @ManyToOne
    @JoinColumn(name = "health_id")
    private HFunctionalItem hFunctionalItem; // 건강 기능 ID 외래키
}
