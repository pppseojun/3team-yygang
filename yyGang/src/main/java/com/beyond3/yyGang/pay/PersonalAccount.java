package com.beyond3.yyGang.pay;

import com.beyond3.yyGang.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "personal_account")
public class PersonalAccount {

    @Id
    @GeneratedValue
    @Column(name = "personal_account_id")
    private Long pId;

    private int balance;

    private String bankName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
