package com.usermicroservice.usermicroservice.config;

import com.usermicroservice.usermicroservice.config.property.SecurityApplicationProperties;
import com.usermicroservice.usermicroservice.webservices.AuthWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Classe de configuration qui va définir la gestion des requêtes.
 */
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final SecurityApplicationProperties securityApplicationProperties;
    @Autowired
    private AuthWebService _authWebService;

    public WebConfig(SecurityApplicationProperties securityApplicationProperties) {
        this.securityApplicationProperties = securityApplicationProperties;
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
                .antMatchers(HttpMethod.POST, "/users/signup", "/users/login").permitAll()
                .antMatchers(HttpMethod.GET, "/users/{id}", "/users/current").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthFilter(authenticationManager(), securityApplicationProperties, _authWebService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Création d'un Bean qui va définir la stratégie du CORS sur l'API.
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
