package com.isaac.demo.app.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_tbl")
@Getter @Setter
public class User {
    @Id @GeneratedValue
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
}
