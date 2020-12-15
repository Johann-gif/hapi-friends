package com.example.hapifriends.User;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.PersistenceContext;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @PersistenceContext

    List<User> findBySurnameStartsWithIgnoreCase(String surname);
    List<User> findByFirstnameStartsWithIgnoreCase(String firstname);
}
