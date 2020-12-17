package com.example.hapifriends.Post;

import com.example.hapifriends.User.User;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThisPost (@PathVariable int id) throws ResourceNotFoundException {
        Post myPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));

        postRepository.delete(myPost);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<Post> UpdatePost(@PathVariable int id, @RequestParam(required = false) String title_post,
                                           @RequestParam(required = false) String text_post,
                                           @RequestParam(required = false) Boolean public_post) throws ResourceNotFoundException{
        Post myPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found :: " + id));
        if (title_post != null) {
            myPost.setTitle(title_post);
        }
        if (text_post != null) {
            myPost.setText(text_post);
        }
        if (public_post != null) {
            myPost.setIspublic(public_post);
        }
        postRepository.save(myPost);
        return ResponseEntity.ok().body(myPost);
    }

    @GetMapping("/search/{name}")
    public @ResponseBody List<Post> GetPostsByName(@PathVariable String name) {
        List<Post> myPostsTitle = postRepository.findByTitleIsContainingIgnoreCase(name);
        return myPostsTitle;
    }

    @GetMapping("/searchText/{name}")
    public @ResponseBody List<Post> GetPostsByText(@PathVariable String name) {
        List<Post> myPostsText = postRepository.findByTextIsContainingIgnoreCase(name);
        return myPostsText;
    }
}
