package com.authmicroservice.authmicroservice.services;

import com.asi.lib.dto.UserDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.authmicroservice.authmicroservice.config.property.SecurityApplicationProperties;
import com.authmicroservice.authmicroservice.services.iservices.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

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

    public Boolean verifyJWTToken(String token) {
        try {
            DecodedJWT decodedJWT = getDecodedJWT(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public UserDTO getUser(String token) throws Exception {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        UserDTO userDTO = new ObjectMapper().readValue(decodedJWT.getSubject(), UserDTO.class);
        if (userDTO == null) {
            throw new Exception("Bad Token");
        }
        return userDTO;
    }

    private DecodedJWT getDecodedJWT(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC512(securityApplicationProperties.getSecret().getBytes(StandardCharsets.UTF_8));
        return JWT.require(algorithm)
                .build()
                .verify(token.replace(securityApplicationProperties.getTokenPrefix(), ""));
    }
}
