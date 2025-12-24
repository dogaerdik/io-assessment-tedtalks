package com.io.tedtalks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TedTalksApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TedTalksApplication.class, args);
    }
}

