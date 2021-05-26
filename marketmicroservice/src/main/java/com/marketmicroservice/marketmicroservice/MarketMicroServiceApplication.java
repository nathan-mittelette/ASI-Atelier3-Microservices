package com.marketmicroservice.marketmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class MarketMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketMicroServiceApplication.class, args);
    }

}
