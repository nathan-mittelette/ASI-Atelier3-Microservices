package com.authmicroservice.authmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.asi.lib", "com.authmicroservice.authmicroservice"})
public class AuthMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthMicroServiceApplication.class, args);
    }

}
