package com.example.hapifriends.Friend.Controller;

import com.example.hapifriends.User.Entity.User;
import com.example.hapifriends.User.Repository.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/friends")
public class FriendController {
    private UserRepository userRepository;

    @GetMapping
    public List<User> getFriends(@PathVariable int user_id) throws ResourceNotFoundException
    {
        User user = userRepository.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + user_id));

        return user.getFriends();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getFriend(@PathVariable int owner_id, @PathVariable int friend_id) throws ResourceNotFoundException {
        User owner = userRepository.findById(owner_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
        User friend = userRepository.findById(friend_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + friend_id));

        if (owner.getFriends().contains(friend)) {
            return ResponseEntity.ok().body(friend);
        }
        return ResponseEntity.notFound().build();
    }

   @GetMapping("/search/{name}")
    public @ResponseBody List<User> getFriendsByName(@PathVariable int owner_id, @PathVariable String name) throws ResourceNotFoundException {
       User owner = userRepository.findById(owner_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
       List<User> result = new ArrayList<>();
       List<User> friends = owner.getFriends();

       for (User friend : friends) {
           if (friend.getPseudo().contains(name)) {
               result.add(friend);
           }
       }
       return result;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addFriend(@RequestParam int owner_id, @RequestParam int to_add_id) throws ResourceNotFoundException {
        User owner = userRepository.findById(owner_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
        User to_add = userRepository.findById(to_add_id).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + to_add_id));

        owner.addFriend(to_add);
        to_add.addFriend(owner);

        userRepository.save(owner);
        userRepository.save(to_add);

        return ResponseEntity.ok().body(to_add);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFriend(@PathVariable int owner_id, @PathVariable int to_delete_id) throws ResourceNotFoundException {
        User owner = userRepository.findById(owner_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + owner_id));
        User to_delete = userRepository.findById(to_delete_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + to_delete_id));

        if (!owner.getFriends().remove(to_delete)) {
            return ResponseEntity.notFound().build();
        }
        to_delete.getFriends().remove(owner);
        userRepository.save(owner);
        userRepository.save(to_delete);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
