package com.usermicroservice.usermicroservice.services;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.UserLoginDTO;
import com.asi.lib.services.CrudService;
import com.asi.lib.webservices.AuthWebService;
import com.asi.lib.webservices.CardWebService;
import com.usermicroservice.usermicroservice.mapper.UserMapper;
import com.usermicroservice.usermicroservice.models.User;
import com.usermicroservice.usermicroservice.repositories.UserRepository;
import com.usermicroservice.usermicroservice.services.iservices.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<User> implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthWebService authWebService;
    private final CardWebService cardWebService;

    UserService(UserRepository repository, UserMapper userMapper, PasswordEncoder passwordEncoder, AuthWebService authWebService, CardWebService cardWebService) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authWebService = authWebService;
        this.cardWebService = cardWebService;
    }

    @Override
    public User createUser(User user) throws DataIntegrityViolationException {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        try {
            user = this.insertOrUpdate(user);
            this.cardWebService.createRandomCard(user.getId());
            return user;
        } catch (DataIntegrityViolationException e) {
            throw e;
        }
    }

    public String login(UserLoginDTO userLoginDTO) throws Exception {
        User user = ((UserRepository) this._repository).findByEmail(userLoginDTO.getLogin()).orElseThrow(() -> new Exception("email not found"));

        /*if (!this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new Exception("Bad password");
        }
*/
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
