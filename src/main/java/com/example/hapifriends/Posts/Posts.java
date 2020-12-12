package com.example.hapifriends;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    private int id;
    private String text;
    private Boolean publicPost;
    private String idAuthor;
}
