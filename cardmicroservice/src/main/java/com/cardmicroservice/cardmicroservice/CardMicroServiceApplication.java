package com.cardmicroservice.cardmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.asi.lib", "com.cardmicroservice.cardmicroservice"}
)
public class CardMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardMicroServiceApplication.class, args);
    }

}
