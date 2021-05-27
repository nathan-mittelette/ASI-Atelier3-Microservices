package com.authmicroservice.authmicroservice.controllers;

import com.authmicroservice.authmicroservice.services.iservices.IAuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/cards")
public class AuthController {
    private final IAuthService _marketService;

    public AuthController(com.authmicroservice.authmicroservice.services.iservices.IAuthService marketService) {
        _marketService = marketService;
    }
}
