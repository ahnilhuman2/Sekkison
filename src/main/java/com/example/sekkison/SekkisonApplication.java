package com.example.sekkison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SekkisonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SekkisonApplication.class, args);
    }

}
