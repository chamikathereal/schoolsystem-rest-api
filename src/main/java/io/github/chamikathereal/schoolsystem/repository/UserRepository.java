package io.github.chamikathereal.schoolsystem.repository;

import io.github.chamikathereal.schoolsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Finds a user by email ( SELECT * FROM users WHERE email = ? )
    Optional<User> findByEmail(String email);
}