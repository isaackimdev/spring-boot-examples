package com.isaac.springbootjpa.entity;


import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // number 자동 증가
    private int num;
    @Column
    private String name;
    @Column
    private String id;
    @Column
    private String phone;

    public Member() { }

    public Member(int num, String name, String id, String phone) {
        this.num = num;
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    // getter, setter
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Member{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}