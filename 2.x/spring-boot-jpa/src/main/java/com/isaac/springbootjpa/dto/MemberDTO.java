package com.isaac.springbootjpa.dto;

import com.isaac.springbootjpa.entity.Member;

public class MemberDTO {
    // data 를 주고 받는 객체
    private int num;
    private String name;
    private String id;
    private String phone;

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
        return "MemberDTO{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    // toEntity()
    public Member toEntity() {
        // DTO -> Entity 변환
        return new Member( num, name, id, phone);
    }

}
