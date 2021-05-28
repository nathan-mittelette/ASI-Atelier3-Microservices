package com.authmicroservice.authmicroservice.controllers;

import com.authmicroservice.authmicroservice.dto.UserDTO;
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
    public ResponseEntity getJWTToken(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(_authService.verifyJWTToken(token) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/getUser")
    public UserDTO getUser(@RequestHeader("Authorization") String token) throws Exception {
        return _authService.getUser(token);
    }
}
