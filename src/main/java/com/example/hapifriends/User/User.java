package com.example.hapifriends.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    private int id;
    private String surname;
    private String firstname;
    private String email;
    private String mob_number;
}
