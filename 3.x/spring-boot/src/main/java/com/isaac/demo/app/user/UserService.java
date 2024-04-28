package com.isaac.demo.app.user;

import com.isaac.demo.app.security.token.JwtProvider;
import com.isaac.demo.app.user.dto.LoginRequest;
import com.isaac.demo.app.user.dto.UserRequest;
import com.isaac.demo.app.user.dto.UserResponse;
import com.isaac.demo.app.user.entity.User;
import com.isaac.demo.app.user.entity.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse joinUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEncPassword(passwordEncoder.encode(userRequest.getPassword()));

        user.setRoles(Collections.singletonList(UserRole.builder().user(user).name("ROLE_USER").build()));

        userRepository.save(user);

        return UserResponse.from(user); // static factory method 로 return
    }

    public UserResponse updateUser(long id, UserRequest userRequest) {
        Optional<User> findUser = userRepository.findById(id);
        if(!findUser.isPresent()) {
            throw new RuntimeException("User not found.");
        }
        User user = findUser.get();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setPhoneNumber((userRequest.getPhoneNumber()));
        user.setEncPassword("enc_password");

        userRepository.save(user);
        return UserResponse.from(user);
    }

    public UserResponse getUser(long id) {
        Optional<User> findUser = userRepository.findById(id);
        if(!findUser.isPresent()) {
            throw new RuntimeException("User not found.");
        }
        return UserResponse.from(findUser.get());
    }

    public Long removeUser(long id) {
        userRepository.deleteById(id);;
        return id;
    }

    public UserResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new BadCredentialsException("Invalid Email Info"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getEncPassword())) {
            throw new BadCredentialsException("Invalid passowrd");
        }

        return UserResponse.from(user, jwtProvider.createToken(user.getEmail(), user.getRoles()));
    }

}
