package com.example.hapifriends.Friend.Controller;

import com.example.hapifriends.User.User;
import com.example.hapifriends.User.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/friends")
public class FriendController {
    private UserRepository userRepository;

    public Iterable<User> getFriends()
    {
        return userRepository.findAll();
    }
}
