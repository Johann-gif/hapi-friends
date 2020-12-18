package com.example.hapifriends.Friend.Repository;

import com.example.hapifriends.Friend.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
