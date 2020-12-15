package com.example.hapifriends.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    private int id;
    private String textPost;
    private Boolean publicPost;
    private int idAuthor;
    private int reaction;

}
