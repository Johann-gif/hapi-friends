package com.example.hapifriends.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text_post;
    private Boolean public_post;
    private int id_author;
    private int reaction;

}
