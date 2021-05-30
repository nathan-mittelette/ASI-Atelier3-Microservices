package com.usermicroservice.usermicroservice.config;

import com.asi.lib.config.AbstractWebConfig;
import com.asi.lib.webservices.AuthWebService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
public class WebConfig extends AbstractWebConfig {

    public WebConfig(AuthWebService authWebService) {
        super(authWebService);
    }
}
