package dev.isaac.springbootsecurityjwt.repositories;

import dev.isaac.springbootsecurityjwt.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities") // Eager 조회
    Optional<User> findOneWithAuthoritiesByUsername(String username);

}
