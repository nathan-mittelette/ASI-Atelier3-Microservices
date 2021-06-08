package com.asi.lib.config;

import com.asi.lib.webservices.AuthWebService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public abstract class AbstractWebConfig extends WebSecurityConfigurerAdapter {

    private final AuthWebService authWebService;

    public AbstractWebConfig(AuthWebService authWebService) {
        this.authWebService = authWebService;
    }

    /**
     * Configuration des filtres à appliquer aux requêtes.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers().permitAll()
                // Tous ce qui est public est authorisé
                .antMatchers("/public/**").permitAll()
                // Tous ce qui est private est authorisé car c'est une communication Microservice to Microservice
                .antMatchers("/private/**").permitAll()
                // Tous ce qui commence par secured doit être authentifié.
                .antMatchers("/secured/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AbstractAuthFilter(authenticationManager(), this.authWebService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Création d'un Bean qui va définir la stratégie du CORS sur l'API.
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
