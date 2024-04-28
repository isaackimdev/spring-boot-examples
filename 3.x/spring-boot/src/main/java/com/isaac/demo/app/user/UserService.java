package com.isaac.demo.app.user;

import com.isaac.demo.app.user.dto.LoginRequest;
import com.isaac.demo.app.user.dto.UserRequest;
import com.isaac.demo.app.user.dto.UserResponse;

public interface UserService {
    UserResponse joinUser(UserRequest userRequest);
    UserResponse updateUser(long id, UserRequest userRequest);
    UserResponse getUser(long id);
    Long removeUser(long id);
    UserResponse login(LoginRequest loginRequest);
}
