package com.example.hapifriends.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findBySurnameStartsWithIgnoreCase(String surname);
    List<User> findByFirstnameStartsWithIgnoreCase(String firstname);
    Optional<User> findUserByUsername(String username);
}
