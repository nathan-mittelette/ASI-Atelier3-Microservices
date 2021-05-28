package com.authmicroservice.authmicroservice.services;

import com.authmicroservice.authmicroservice.config.property.SecurityApplicationProperties;
import com.authmicroservice.authmicroservice.dto.UserDTO;
import com.authmicroservice.authmicroservice.services.iservices.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class AuthService implements IAuthService {

    private final SecurityApplicationProperties securityApplicationProperties;

    public AuthService(SecurityApplicationProperties securityApplicationProperties) {
        this.securityApplicationProperties = securityApplicationProperties;
    }

    @Override
    public String getJWTToken(UserDTO userDTO) throws JsonProcessingException {
        String tokenSubject = new ObjectMapper().writeValueAsString(userDTO);

        // Création du JWT token en correspondance avec l'utilisateur.
        String token = JWT.create()
                // Ajout des données que l'on souhaite stocker que les Token.
                // Dans l'exemple nous décidons de stoker uniquement le username de l'utilisateur.
                .withSubject(tokenSubject)
                // Ajout de la date d'expiration du token.
                .withExpiresAt(new Date(System.currentTimeMillis() + securityApplicationProperties.getExpirationTime()))
                // Ajout de la signature du token avec le secret.
                .sign(Algorithm.HMAC512(securityApplicationProperties.getSecret().getBytes(StandardCharsets.UTF_8)));
        return token;
    }
}
