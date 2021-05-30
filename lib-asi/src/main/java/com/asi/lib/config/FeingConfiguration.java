package com.asi.lib.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.asi.lib.webservices")
public class FeingConfiguration {
}
