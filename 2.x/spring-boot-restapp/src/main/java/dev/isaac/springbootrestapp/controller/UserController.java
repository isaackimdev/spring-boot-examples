package dev.isaac.springbootrestapp.controller;

import dev.isaac.springbootrestapp.model.User;
import dev.isaac.springbootrestapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // Logger 는 보통 static으로 잡는다.
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAllUsers() { // JSON 으로 변환하여 반환한다.
        return userService.getUsers();
    }

    @GetMapping("/{userid}")
    public User getUserByUserid(@PathVariable Integer userid) {
        logger.debug("" + userid);
        return userService.getUserById(userid);
    }

    @PostMapping("")
    public User registUser(@RequestBody User user) {    // Post 의 body 를 바로 받겠다.
        return userService.registUser(user);
    }

    @PutMapping("/{userid}")
    public void modifyUser(@PathVariable Integer userid,
                           @RequestBody User user) {
        userService.modifyUser(userid, user);
    }

    @DeleteMapping("/{userid}")
    public void removeUser(@PathVariable Integer userid) {
        userService.removeUser(userid);
    }


}
