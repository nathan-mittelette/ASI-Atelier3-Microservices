package com.usermicroservice.usermicroservice.services;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import com.usermicroservice.usermicroservice.dto.UserLoginDTO;
import com.usermicroservice.usermicroservice.mapper.UserMapper;
import com.usermicroservice.usermicroservice.models.User;
import com.usermicroservice.usermicroservice.repositories.UserRepository;
import com.usermicroservice.usermicroservice.services.iservices.IUserService;
import com.usermicroservice.usermicroservice.webservices.AuthWebService;
import com.usermicroservice.usermicroservice.webservices.CardWebService;
import feign.Feign;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User> implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthWebService authWebService;
    private final CardWebService cardWebService;

    UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder, UserMapper userMapper) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authWebService = Feign.builder()
                .target(AuthWebService.class, "http://localhost:5000");
        this.cardWebService = Feign.builder()
                .target(CardWebService.class, "http://localhost:5001");
    }

    @Override
    public User createUser(User user) throws DataIntegrityViolationException {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        try {
            user = this.insertOrUpdate(user);
            this.cardWebService.createRandomCard(userMapper.fromUser(user));
            return user;
        }
        catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    public String login(UserLoginDTO userLoginDTO) throws Exception {
        User user = ((UserRepository) this._repository).findByEmail(userLoginDTO.getLogin()).orElseThrow(() -> new Exception("email not found"));

        if (!this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Bad password");
        }

        return this.authWebService.getJWTToken(this.userMapper.fromUser(user));
    }

    @Override
    public UserDTO getCurrent(UserDTO userDTO) {
        Long money = ((UserRepository) this._repository).getMoney(userDTO.getId());
        userDTO.setMoney(money);
        return userDTO;
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return _repository.findById(id).orElseThrow(() -> new Exception("User not found."));
    }
}
