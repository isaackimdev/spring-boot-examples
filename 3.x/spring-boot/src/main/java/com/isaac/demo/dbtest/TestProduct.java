package com.isaac.demo.dbtest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class TestProduct {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private long price;

}
