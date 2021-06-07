package com.roommicroservice.roommicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.asi.lib", "com.roommicroservice.roommicroservice"}
)
public class RoomMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoomMicroServiceApplication.class, args);
    }

}
