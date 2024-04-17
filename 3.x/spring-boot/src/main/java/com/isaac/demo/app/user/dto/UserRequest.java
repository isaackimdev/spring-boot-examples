package com.isaac.demo.app.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRequest {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;
}
