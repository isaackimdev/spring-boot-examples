package com.isaac.springbootmybatis.controller;

import com.isaac.springbootmybatis.dto.MemberDto;
import com.isaac.springbootmybatis.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberMapper memberMapper;

    @GetMapping("/users/count")
    public Map getCountOfUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("count", memberMapper.getCountOfMember());
        return map;
    }

    @GetMapping("/users")
    public Map getUsers() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", memberMapper.getMembers());
        return map;
    }

    @PostMapping("/users")
    public Map registerUser(@RequestBody MemberDto memberDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", memberMapper.registerMember(memberDto));
        return map;
    }


}
