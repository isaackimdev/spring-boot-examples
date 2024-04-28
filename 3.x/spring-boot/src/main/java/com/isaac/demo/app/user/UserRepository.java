package com.isaac.demo.app.user;

import com.isaac.demo.app.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 없을 수도 있기 때문에 Optional
    Optional<User> findByEmail(String email);
}
