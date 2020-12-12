package com.example.hapifriends.Posts;

import com.example.hapifriends.Posts;
import com.example.hapifriends.PostsRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    @Autowired
    private PostsRepository postsRepository;
    @GetMapping
    public List<com.example.hapifriends.Post> getPost() {
        return postsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> GetThisPost(@PathVariable int id) throws ResourceNotFoundException {
        Post i = postsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));
        return ResponseEntity.ok().body(i);
    }
}
