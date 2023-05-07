package com.isaac.springbootmybatis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserDto {
    private String name;
    private int age;
}
