package com.btt.pay.repository;

import com.btt.pay.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u.loginAttempts FROM User u WHERE u.username = :username")
    Optional<Integer> findAttemptsByUsername(@Param("username") String username);
}
