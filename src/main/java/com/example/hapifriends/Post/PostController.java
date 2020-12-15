package com.example.hapifriends.Post;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostController {
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
}
