package com.example.hapifriends.Post.Entity;

import com.example.hapifriends.User.Entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;
    @Column(columnDefinition = "boolean default false")
    private Boolean shared;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("friends")
    private User author;
}
