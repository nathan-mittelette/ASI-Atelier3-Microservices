package com.cardmicroservice.cardmicroservice.config;

import com.asi.lib.config.AbstractWebConfig;
import com.asi.lib.webservices.AuthWebService;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public class WebConfig extends AbstractWebConfig {

    public WebConfig(AuthWebService authWebService) {
        super(authWebService);
    }
}
