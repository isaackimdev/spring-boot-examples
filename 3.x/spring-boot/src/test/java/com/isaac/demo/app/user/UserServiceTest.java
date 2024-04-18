package com.isaac.demo.app.user;

import com.isaac.demo.app.user.dto.UserRequest;
import com.isaac.demo.app.user.dto.UserResponse;
import com.isaac.demo.app.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    // 가상으로 만들 것
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    public void joinUserTest() {
        User user = new User();
        user.setId(1L);
        user.setName("kim");
        user.setAddress("seoul");
        user.setEmail("abc@test.com");
        user.setPhoneNumber("0101234567");
        user.setEncPassword("1234qwer1234");

        UserRequest userRequest = UserRequest.builder()
                .name(user.getName())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password("12345")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        UserResponse userResponse = userService.joinUser(userRequest);

        assertEquals("kim", userResponse.getName());
        assertEquals("abc@test.com", userResponse.getEmail());
        assertEquals("0101234567", userResponse.getPhoneNumber());
        assertEquals("seoul", userResponse.getAddress());
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(1L);
        user.setName("kim");
        user.setAddress("seoul");
        user.setEmail("abc@test.com");
        user.setPhoneNumber("0101234567");
        user.setEncPassword("1234qwer1234");

        UserRequest userRequest = UserRequest.builder()
                .name(user.getName())
                .address("euijeongbu")
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password("12345")
                .build();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserResponse updateUserResponse = userService.updateUser(1L, userRequest);

        assertEquals("euijeongbu", updateUserResponse.getAddress());
    }
}