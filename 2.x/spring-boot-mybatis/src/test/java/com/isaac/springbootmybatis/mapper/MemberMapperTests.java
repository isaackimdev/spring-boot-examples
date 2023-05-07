package com.isaac.springbootmybatis.mapper;

import com.isaac.springbootmybatis.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;


    @Test
    @DisplayName("getMembersTest")
    public void getMembersTest() {
        List<MemberDto> list = memberMapper.getMembers();
        System.out.println("list size : " + list.size());
        System.out.println("list : " +list);
        System.out.println("list class name : " + list.getClass().getName());
        if (list.size() > 0) {
            System.out.println("list class inner class : " + list.get(0).getClass().getName());
        }

        list.stream().forEach(memberDto -> {
            System.out.println(memberDto.toString());
        });
    }

    @Test
    @DisplayName("registerUserTest")
    @Transactional
    public void registerUserTest() {
        int result = memberMapper.registerMember(MemberDto.builder()
                .name("jun")
                .age(25)
                .email("jun@example.com")
                .build());
        System.out.println("result : " + result);
    }


}
