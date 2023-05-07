package com.isaac.springbootmybatis.mapper;

import com.isaac.springbootmybatis.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    @DisplayName("getUsersTest")
    public void getUsersTest() {
        List<Map<String, Object>> list = userMapper.getUsers();
        System.out.println("list size : " + list.size());
        System.out.println("list : " +list);
        System.out.println("list class name : " + list.getClass().getName());
        System.out.println("list class inner class : " + list.get(0).getClass().getName());
    }

    @Test
    @DisplayName("Mybatis 회원 등록 테스트")
    public void registerUserTest() {
        int result = userMapper.registerUser(UserDto.builder()
                .name("jun")
                .age(25)
                .build());
        System.out.println("result : " + result);
    }


}
