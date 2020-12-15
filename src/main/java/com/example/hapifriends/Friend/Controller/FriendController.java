package com.example.hapifriends.Friend.Controller;

import com.example.hapifriends.User.User;
import com.example.hapifriends.User.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path="/friends")
public class FriendController {
    private UserRepository userRepository;

    @GetMapping
    public List<User> getFriends()
    {
        // Get user with authentication
        User user = new User();
        // Get user with authentication

        return user.getFriends();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getFriend(@PathVariable int user_id) throws ResourceNotFoundException {
        // Get user with authentication
        User currentUser = new User();
        // Get user with authentication

        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id));
        if (currentUser.getFriends().contains(user)) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/{name}")
    public @ResponseBody List<User> getUsersByName(@PathVariable String name) {
        // Get user with authentication
        User currentUser = new User();
        // Get user with authentication

        List<User> myUsersSurname = userRepository.findBySurnameStartsWithIgnoreCase(name);
        List<User> myUsersFirstname = userRepository.findByFirstnameStartsWithIgnoreCase(name);
        List<User> myUsers = Stream.concat(myUsersSurname.stream(), myUsersFirstname.stream())
                .collect(Collectors.toList());
        return myUsers;
    }

    @PostMapping("/add")
    public void addFriend(@RequestBody int user_id) throws ResourceNotFoundException {
        // Get user with authentication
        User currentUser = new User();
        // Get user with authentication

        User user_to_add = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id));
        currentUser.getFriends().add(user_to_add);
        userRepository.save(currentUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable int user_id) throws ResourceNotFoundException {
        // Get user with authentication
        User currentUser = new User();
        // Get user with authentication

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id));

        if (!currentUser.getFriends().remove(user)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
