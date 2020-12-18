package com.example.hapifriends.Friend.Controller;

import com.example.hapifriends.User.Entity.User;
import com.example.hapifriends.User.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/friends")
public class FriendController {
    private UserRepository userRepository;
    private User currentUser;

    public FriendController() {
        currentUser = new User();
        currentUser.setId(4);
        currentUser.setPseudo("as");
        currentUser.setPassword("as");
        currentUser.setSurname("Schulz");
        currentUser.setFirstname("Antoine");
        currentUser.setEmail("antoine.schulz@lacatholille.fr");
        currentUser.setMob_number("");
    }

    @GetMapping
    public List<User> getFriends()
    {
        return currentUser.getFriends();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getFriend(@PathVariable int user_id) throws ResourceNotFoundException {
        User friend = userRepository.getFriendIfExists(currentUser.getId(), user_id);
        if (friend != null) {
            return ResponseEntity.ok().body(friend);
        }
        return ResponseEntity.notFound().build();
    }

   @GetMapping("/search/{name}")
    public @ResponseBody List<User> getFriendsByName(@PathVariable String name) {
       List<User> result = new ArrayList<>();
        List<User> friends = currentUser.getFriends();
        for (User friend : friends) {
            if (friend.getPseudo().contains(name)) {
                result.add(friend);
            }
        }
        return result;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addFriend(@RequestParam int user_id) throws ResourceNotFoundException {
        User user_to_add = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id));

        currentUser.addFriend(user_to_add);
        user_to_add.addFriend(currentUser);

        userRepository.save(currentUser);
        userRepository.save(user_to_add);

        return ResponseEntity.ok().body(user_to_add);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable int user_id) throws ResourceNotFoundException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id));

        if (!currentUser.getFriends().remove(user)) {
            return ResponseEntity.notFound().build();
        }
        user.getFriends().remove(currentUser);
        userRepository.save(currentUser);
        userRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
