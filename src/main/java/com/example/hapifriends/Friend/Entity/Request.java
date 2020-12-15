package com.example.hapifriends.Friend.Entity;

import com.example.hapifriends.User.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private User user1;
    @ManyToOne
    private User user2;
}
