package com.example.hapifriends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HapifriendsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HapifriendsApplication.class, args);
    }

    @Bean
    @Primary
    public BCryptPasswordEncoder getpce()
    {
        return new BCryptPasswordEncoder();
    }

}
