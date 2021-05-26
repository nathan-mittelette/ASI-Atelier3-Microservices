package com.usermicroservice.usermicroservice.services;

import com.usermicroservice.usermicroservice.config.property.SecurityApplicationProperties;
import com.usermicroservice.usermicroservice.dto.UserDTO;
import com.usermicroservice.usermicroservice.dto.UserLoginDTO;
import com.usermicroservice.usermicroservice.mapper.UserMapper;
import com.usermicroservice.usermicroservice.models.User;
import com.usermicroservice.usermicroservice.repositories.UserRepository;
import com.usermicroservice.usermicroservice.services.iservices.IUserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class UserService extends CrudService<User> implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final SecurityApplicationProperties securityApplicationProperties;

    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper, SecurityApplicationProperties securityApplicationProperties) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.securityApplicationProperties = securityApplicationProperties;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.insertOrUpdate(user);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) throws Exception {
        User user = ((UserRepository) this._repository).findByEmail(userLoginDTO.getLogin()).orElseThrow(() -> new Exception("email not found"));

        if (!this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Bad password");
        }

        UserDTO userDTO = this.userMapper.fromUser(user);

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

    @Override
    public UserDTO getCurrent(UserDTO userDTO) {
        Long money = ((UserRepository) this._repository).getMoney(userDTO.getId());
        userDTO.setMoney(money);
        return userDTO;
    }
}
