package dev.isaac.springbootrestapp.dao;

import dev.isaac.springbootrestapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    public static List<User> users;

    static {
        users = new ArrayList<>();
        users.add(new User(100, "kim"));
        users.add(new User(200, "lee"));
        users.add(new User(300, "choi"));
        users.add(new User(400, "park"));
        users.add(new User(500, "kwon"));
        users.add(new User(600, "hwang"));
    }

    // 사용자 전체보기
    public List<User> getAllUsers() {
        return users;
    }
    // 사용자 상세보기
    public User getUserByUserid(Integer userid) {
        return users.stream()
                .filter(user -> user.getUserid() == userid)
                .findAny()
                .orElse(new User(0, "no user"));
    }

    // 사용자 등록
    public User insertUser(User user) {
        users.add(user);
        return user;
    }

    // 사용자 수정
    public void updateUser(User user) {
        users.stream()
                .filter(item -> item.getUserid().equals(user.getUserid()))
                .findAny()
                .orElse(new User(0, "no user"))
                .setUsername(user.getUsername());
    }

    // 사용자 삭제
    public void deleteUser(Integer userid) {
        users.removeIf(user -> user.getUserid().equals(userid));
    }

}

