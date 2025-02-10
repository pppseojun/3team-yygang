package com.beyond3.yyGang.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_ID")
    private Long roleID;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Role_name rolename;

    @OneToMany(mappedBy = "role")
    private List<Users> users;
}
