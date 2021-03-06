package com.authmicroservice.authmicroservice.controllers;

import com.asi.lib.dto.UserDTO;
import com.authmicroservice.authmicroservice.services.iservices.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/private/auth")
public class AuthController {
    private final IAuthService _authService;

    public AuthController(IAuthService authService) {
        _authService = authService;
    }

    @PostMapping(value = "/getJWTToken")
    public String getJWTToken(@RequestBody UserDTO userDTO) throws JsonProcessingException {
        return _authService.getJWTToken(userDTO);
    }

    @GetMapping(value = "/verifyJWTToken")
    public ResponseEntity<Boolean> verifyJWTToken(@RequestHeader(value="Authorization") String token) {
        return _authService.verifyJWTToken(token) ? new ResponseEntity<Boolean>(true, HttpStatus.OK) : new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
    }

    @PostMapping(value = "/getUser")
    public UserDTO getUser(@RequestBody String token) throws Exception {
        return _authService.getUser(token);
    }
}
