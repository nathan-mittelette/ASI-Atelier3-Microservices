package com.usermicroservice.usermicroservice.services.iservices;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import com.usermicroservice.usermicroservice.dto.UserLoginDTO;
import com.usermicroservice.usermicroservice.models.User;

public interface IUserService extends ICrudService<User> {
    User createUser(User user);
    UserDTO getCurrent(UserDTO userDTO);
    User findUserById(Long id) throws Exception;
    String login(UserLoginDTO userLoginDTO) throws Exception;
}
