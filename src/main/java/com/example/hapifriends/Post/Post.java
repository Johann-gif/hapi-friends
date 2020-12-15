package com.example.hapifriends.Post;

import com.example.hapifriends.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text_post;
    private Boolean public_post;
    private int reaction;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("friends")
    private User user;


}
