package com.example.hapifriends.User;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> GetThisUser(@PathVariable int id) throws ResourceNotFoundException {
        User i = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
        return ResponseEntity.ok().body(i);
    }

    @PostMapping()
    public ResponseEntity<User> addThisUser (@RequestParam int id, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String numero) {
        User myUser = new User();
        myUser.setId(id);
        myUser.setSurname(nom);
        myUser.setFirstname(prenom);
        myUser.setEmail(email);
        myUser.setMob_number(numero);
        userRepository.save(myUser);
        return ResponseEntity.ok().body(myUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThisUser (@PathVariable int id) throws ResourceNotFoundException {
        User myUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));

        List<User> friends = myUser.getFriends();
        for (User friend : friends) {
            friend.getFriends().remove(myUser);
        }
        myUser.getFriends().removeAll(myUser.getFriends());

        userRepository.delete(myUser);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<User> changeClient (@PathVariable int id, @RequestParam(required = false) String nom, @RequestParam(required = false) String prenom, @RequestParam(required = false) String email, @RequestParam(required = false) String numero) throws ResourceNotFoundException {
        User myUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id));
        if(nom!=null) {
            myUser.setSurname(nom);
        }
        if(prenom!=null) {
            myUser.setFirstname(prenom);
        }
        if(email!=null) {
            myUser.setEmail(email);
        }
        if(numero!=null) {
            myUser.setMob_number(numero);
        }
        userRepository.save(myUser);
        return ResponseEntity.ok().body(myUser);
    }

    @GetMapping("/search/{name}")
    public @ResponseBody List<User> GetUsersByName(@PathVariable String name) {
        List<User> myUsersSurname = userRepository.findBySurnameStartsWithIgnoreCase(name);
        List<User> myUsersFirstname = userRepository.findByFirstnameStartsWithIgnoreCase(name);
        List<User> myUsers = Stream.concat(myUsersSurname.stream(), myUsersFirstname.stream())
                .collect(Collectors.toList());
        return myUsers;
    }
}
