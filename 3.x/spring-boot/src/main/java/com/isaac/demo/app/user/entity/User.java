package com.isaac.demo.app.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    private String address;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String encPassword;

    // EAGER Fetch Type
    // 엔티티 조회시에 바로 연관관계에 있는 모든 Entity를 가져옴
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserRole> roles = new ArrayList<>();

    public void giveUserRoles(List<UserRole> userRoles) {
        this.roles = userRoles;
        roles.forEach(userRole -> userRole.setUser(this));
    }

}
