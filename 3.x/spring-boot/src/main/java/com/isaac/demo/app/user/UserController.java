package com.isaac.demo.app.user;

import com.isaac.demo.app.user.dto.LoginRequest;
import com.isaac.demo.app.user.dto.UserRequest;
import com.isaac.demo.app.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @GetMapping("/users/check")
    public void healthCheck() {
        log.info("user api is healthy!!");
    }

    @PostMapping("/sign")
    public ResponseEntity<UserResponse> postUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(userService.joinUser(userRequest));
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> putUser(@PathVariable("id") long id ,@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(userService.updateUser(id, userRequest));
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Map<String, Long>> deleteUser(@PathVariable("id") long id) {
        Map<String, Long> resposne = Map.of("id", id);
        return ResponseEntity.ok().body(resposne);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
        UserResponse userResponse = userService.login(request);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("Token", userResponse.getToken());
        return ResponseEntity.ok().headers(responseHeader).body(userResponse);
    }
}
