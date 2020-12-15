package com.example.hapifriends.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findBySurnameStartsWithIgnoreCase(String surname);
    List<User> findByFirstnameStartsWithIgnoreCase(String firstname);
    @Query(value = "SELECT f.user_id FROM friends f WHERE f.user_id = :id1 and f.friend_id = :id2",
            nativeQuery = true)
    Integer IdIsFriend(
            @Param("id1") Integer id1, @Param("id2") Integer id2);
}
