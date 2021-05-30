package com.usermicroservice.usermicroservice.controllers;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.UserLoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.usermicroservice.usermicroservice.exception.ConflictException;
import com.usermicroservice.usermicroservice.mapper.UserMapper;
import com.usermicroservice.usermicroservice.models.User;
import com.usermicroservice.usermicroservice.services.iservices.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/public/users")
public class UserController {
    private final IUserService _userService;
    private UserMapper _userMapper;

    public UserController(IUserService userService, UserMapper userMapper) {
        _userService = userService;
        _userMapper = userMapper;
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable Long id) throws Exception {
        return this._userMapper.fromUser(_userService.findUserById(id));
    }

    @PostMapping(value = "/signup")
    public void signUp(@RequestBody User user) throws JsonProcessingException, DataIntegrityViolationException {
        try {
            this._userService.createUser(user);
        }
        catch (DataIntegrityViolationException e) {
            throw new ConflictException("Email already assigned :" + user.getEmail());
        }
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody UserLoginDTO userLoginDTO) throws Exception {
        return this._userService.login(userLoginDTO);
    }

    @GetMapping(value = "/current")
    public UserDTO getCurrent(@AuthenticationPrincipal final UserDTO userDTO) {
        return this._userService.getCurrent(userDTO);
    }
}
