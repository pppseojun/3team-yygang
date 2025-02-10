package com.beyond3.yyGang.domain.store;

import com.beyond3.yyGang.domain.member.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cart_ID")
    private Long cartID;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_ID")
    private Users user;

    @OneToMany(mappedBy = "cart")
    private List<CartOption> cartOptions;
}
