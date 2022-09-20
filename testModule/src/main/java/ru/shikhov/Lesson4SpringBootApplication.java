package ru.shikhov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.shikhov.model.Cart;
import ru.shikhov.service.CartService;

@SpringBootApplication
public class Lesson4SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lesson4SpringBootApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
