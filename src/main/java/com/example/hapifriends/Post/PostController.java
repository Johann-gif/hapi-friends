package com.example.hapifriends.Post;

import com.example.hapifriends.User.User;
import com.example.hapifriends.User.UserRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @GetMapping
    public List<Post> getPost() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> GetThisPost(@PathVariable int id) throws ResourceNotFoundException {
        Post i = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));
        return ResponseEntity.ok().body(i);
    }

    //Méthode avec requestParam
    @PostMapping("/add")
    public ResponseEntity<Post> addThisPost (@RequestParam String title, @RequestParam String text, @RequestParam Boolean ispublic, @RequestParam int id_user)  throws ResourceNotFoundException{
        Post myPost = new Post();
        myPost.setTitle(title);
        myPost.setText(text);
        myPost.setIspublic(ispublic);
        myPost.setUser(userRepository.findById(id_user).orElseThrow(() -> new ResourceNotFoundException("User not found :: " + id_user)));
        postRepository.save(myPost);
        return ResponseEntity.ok().body(myPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThisPost (@PathVariable int id) throws ResourceNotFoundException {
        Post myPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));
        postRepository.delete(myPost);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Post> UpdatePost(@PathVariable int id, @RequestParam(required = false) String title,
                                           @RequestParam(required = false) String text,
                                           @RequestParam(required = false) Boolean ispublic) throws ResourceNotFoundException{
        Post myPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));
        if (title != null) {
            myPost.setTitle(title);
        }
        if (text != null) {
            myPost.setText(text);
        }
        if (ispublic != null) {
            myPost.setIspublic(ispublic);
        }
        postRepository.save(myPost);
        return ResponseEntity.ok().body(myPost);
    }
    @GetMapping("/search/{name}")
    public @ResponseBody List<Post> GetPostsByName(@PathVariable String name) {
        List<Post> myPostsTitle = postRepository.findByTitleIsContainingIgnoreCase(name);
        return myPostsTitle;
    }

    @GetMapping("/searchText/{text}")
    public @ResponseBody List<Post> GetPostsByText(@PathVariable String text) {
        List<Post> myPostsText = postRepository.findByTextIsContainingIgnoreCase(text);
        return myPostsText;
    }

}
