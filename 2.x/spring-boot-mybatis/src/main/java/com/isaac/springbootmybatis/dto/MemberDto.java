package com.isaac.springbootmybatis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MemberDto {

    private Long id;
    private String name;
    private int age;
    private String email;

}
