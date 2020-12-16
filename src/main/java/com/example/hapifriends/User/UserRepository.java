package com.example.hapifriends.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
public interface UserRepository extends JpaRepository<User, Integer> {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findBySurnameStartsWithIgnoreCase(String surname);
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findByFirstnameStartsWithIgnoreCase(String firstname);
    @Query(value = "SELECT * FROM user WHERE id = (SELECT f.friend_id FROM friends f WHERE f.user_id = :id1 and f.friend_id = :id2)",
            nativeQuery = true)
    User getFriendIfExists(
            @Param("id1") Integer id1,
            @Param("id2") Integer id2
    );
}
