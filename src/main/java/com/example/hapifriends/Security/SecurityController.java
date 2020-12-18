package com.example.hapifriends.Security;

import com.example.hapifriends.User.Entity.User;
import com.example.hapifriends.User.Repository.UserRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/security")
public class SecurityController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Méthode avec requestBody
    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    //Méthode avec requestParam
    @PostMapping("/sign-up2")
    public ResponseEntity<User> addThisUser (@RequestParam String pseudo, @RequestParam String password, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String numero) {
        User myUser = new User();
        myUser.setPseudo(pseudo);
        myUser.setPassword(bCryptPasswordEncoder.encode(password));
        myUser.setSurname(nom);
        myUser.setFirstname(prenom);
        myUser.setEmail(email);
        myUser.setMob_number(numero);
        userRepository.save(myUser);
        return ResponseEntity.ok().body(myUser);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody String userCredentials) {
        String access_token = "";

        HttpResponse<String> response = Unirest.post("https://dev-h9lk8101.eu.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"K6M8XP8ilUh9lS6bI026Zxw0if804OA6\",\"client_secret\":\"Sb-6bgfEYpYabvXreJrFZQ6ja7veoWZHddEi5z05VHVGRCK9T8LBfWs_HXFB4C1N\",\"audience\":\"https://hapi-friends/\",\"grant_type\":\"client_credentials\"}")
                .asString();
        access_token = response.getBody().toString();


        return access_token;
    }
}
