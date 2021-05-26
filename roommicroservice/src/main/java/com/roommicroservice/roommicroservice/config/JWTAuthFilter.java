package com.roommicroservice.roommicroservice.config;

import com.roommicroservice.roommicroservice.config.property.SecurityApplicationProperties;
import com.roommicroservice.roommicroservice.dto.UserDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JWTAuthFilter extends BasicAuthenticationFilter {

    private final SecurityApplicationProperties securityApplicationProperties;

    public JWTAuthFilter(AuthenticationManager authenticationManager, SecurityApplicationProperties securityApplicationProperties) {
        super(authenticationManager);
        this.securityApplicationProperties = securityApplicationProperties;
    }

    /**
     * Filtre qui permet de récupérer l'utilisateur de la requête et le stocker dans le SecurityContext.
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        // Récupération du header qui contient le token JWT.
        String header = req.getHeader(securityApplicationProperties.getHeaderString());

        // Si jamais il n'y a pas de token JWT on passe à la suite.
        if (header == null || !header.startsWith(securityApplicationProperties.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        // Récupération de l'utilisateur et stockage dans le SecurityContext.
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    /**
     * Retourne une classe UsernamePasswordAuthenticationToken en fonction de la requête
     *
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // Récupération du token JWT présent dans le header HTTP.
        String token = request.getHeader(securityApplicationProperties.getHeaderString());
        if (token != null) {
            // On parse le token pour récupérer les informations dedans
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(securityApplicationProperties.getSecret().getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .verify(token.replace(securityApplicationProperties.getTokenPrefix(), ""));

            // Récupération du username stoké dans le token
            UserDTO userDTO;
            try {
                userDTO = new ObjectMapper().readValue(decodedJWT.getSubject(), UserDTO.class);
            } catch (JsonProcessingException e) {
                return null;
            }

            logger.info("Récupération de l'utilisateur : " + userDTO);

            if (userDTO != null) {
                // Création de l'objet UsernamePasswordAuthenticationToken.
                return new UsernamePasswordAuthenticationToken(userDTO, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
