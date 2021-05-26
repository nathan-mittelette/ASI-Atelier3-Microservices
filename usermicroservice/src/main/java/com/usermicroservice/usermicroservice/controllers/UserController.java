package com.usermicroservice.usermicroservice.controllers;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import com.usermicroservice.usermicroservice.dto.UserLoginDTO;
import com.usermicroservice.usermicroservice.models.User;
import com.usermicroservice.usermicroservice.services.iservices.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final IUserService _userService;

    public UserController(IUserService userService) {
        _userService = userService;
    }

    @PostMapping(value = "/signin")
    public void signIn(@RequestBody User user) throws JsonProcessingException {
        this._userService.createUser(user);
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
