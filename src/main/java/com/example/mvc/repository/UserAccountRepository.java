package com.example.mvc.repository;

import com.example.mvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String Email);
}
