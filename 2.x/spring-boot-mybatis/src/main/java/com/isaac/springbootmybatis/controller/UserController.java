package com.isaac.springbootmybatis.controller;

import com.isaac.springbootmybatis.dto.UserDto;
import com.isaac.springbootmybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;

    @GetMapping("/users/count")
    public Map getCountOfUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("count", userMapper.getCountOfUser());
        return map;
    }

    @GetMapping("/users")
    public Map getUsers() {
        Map<String, Object> map = new HashMap<>();
        map.put("data",userMapper.getUsers());
        return map;
    }

    @PostMapping("/users")
    public Map registerUser(@RequestBody UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", userMapper.registerUser(userDto));
        return map;
    }


}
