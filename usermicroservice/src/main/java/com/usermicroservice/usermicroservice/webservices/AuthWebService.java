package com.usermicroservice.usermicroservice.webservices;

import com.asi.lib.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="auth-micro-service", contextId = "authWebService", url="http://localhost:5000")
public interface AuthWebService {

    @PostMapping("/private/auth/getJWTToken")
    String getJWTToken(UserDTO userDTO);
}
