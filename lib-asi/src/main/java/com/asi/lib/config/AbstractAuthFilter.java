package com.asi.lib.config;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.webservices.AuthWebService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// TODO ajouter la logique d'authentification qui va correspondre à tous les micro services
public class AbstractAuthFilter extends BasicAuthenticationFilter {

    private final AuthWebService authWebService;

    public AbstractAuthFilter(AuthenticationManager authenticationManager, AuthWebService authWebService) {
        super(authenticationManager);
        this.authWebService = authWebService;
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
        String token = req.getHeader("Authorization").replaceAll("Bearer", "");

        // Si jamais il n'y a pas de token JWT on passe à la suite.
        if (token.trim().isEmpty()) {
            chain.doFilter(req, res);
            return;
        }

        // Récupération de l'utilisateur et stockage dans le SecurityContext.
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    /**
     * Retourne une classe UsernamePasswordAuthenticationToken en fonction de la requête
     *
     * @param request
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {

        if (token != null) {
            UserDTO userDTO = this.authWebService.getUser(token);

            logger.info("Récupération de l'utilisateur : " + userDTO);

            if (userDTO != null) {
                // Création de l'objet UsernamePasswordAuthenticationToken.
                return new UsernamePasswordAuthenticationToken(userDTO, null, Collections.<GrantedAuthority>emptyList());
            }
            return null;
        }

        return null;
    }
}
