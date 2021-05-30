package com.asi.lib.webservices;

import com.asi.lib.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-micro-service", contextId = "authWebService", url = "http://localhost:5000")
public interface AuthWebService {

    @PostMapping("/private/auth/getJWTToken")
    String getJWTToken(UserDTO userDTO);

    @GetMapping("/private/auth/verifyJWTToken")
    Boolean verifyJWTToken(String token);

    @GetMapping("/private/auth/getUser")
    UserDTO getUser(String token);
}