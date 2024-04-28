package com.isaac.demo.app.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isaac.demo.app.user.entity.AgeInfo;
import com.isaac.demo.app.user.entity.User;
import com.isaac.demo.app.user.entity.UserRole;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder
public class UserResponse {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private AgeInfo ageInfo;
    private List<UserRole> roles = new ArrayList<>();

    @JsonIgnore
    private String token;

    public static UserResponse from(User user) {
        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .ageInfo(user.getAgeInfo())
                .build();
        user.getRoles().forEach(role -> userResponse.getRoles().add(role));
        return userResponse;
    }

    public static UserResponse from(User user, String token) {
        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .token(token)
                .ageInfo(user.getAgeInfo())
                .build();
        user.getRoles().forEach(role -> userResponse.getRoles().add(role));
        return userResponse;
    }
}
