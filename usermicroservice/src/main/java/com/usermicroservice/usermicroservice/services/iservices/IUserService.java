package com.usermicroservice.usermicroservice.services.iservices;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.UserLoginDTO;
import com.asi.lib.services.iservices.ICrudService;
import com.usermicroservice.usermicroservice.models.User;

public interface IUserService extends ICrudService<User> {
    User createUser(User user);

    UserDTO getCurrent(UserDTO userDTO);

    User findUserById(Long id) throws Exception;

    String login(UserLoginDTO userLoginDTO) throws Exception;

    UserDTO update(UserDTO userDto);
}
