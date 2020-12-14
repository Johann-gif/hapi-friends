package com.example.hapifriends.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String surname;
    private String firstname;
    private String email;
    private String mob_number;
    private String username;
    private String password;
    @ManyToMany()
    @JoinTable(
            name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @JsonIgnoreProperties("friends")
    /*private Set<User> friends = new HashSet<User>();*/
    private List<User> friends;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return null;
    }
    @Override
    public boolean isAccountNonExpired() { return false; }
    @Override
    public boolean isAccountNonLocked() { return false; }
    @Override
    public boolean isCredentialsNonExpired() { return false; }
    @Override
    public boolean isEnabled() { return false; }
}
