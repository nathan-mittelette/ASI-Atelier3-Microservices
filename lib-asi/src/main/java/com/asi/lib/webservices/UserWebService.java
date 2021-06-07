package com.asi.lib.webservices;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "user-micro-service", contextId = "userWebService", url = "http://${service.name.user:localhost}:5004")
public interface UserWebService {

    @GetMapping("/public/users/{id}")
    UserDTO getById(@PathVariable Long id);

    @PutMapping("/private/cards/update")
    UserDTO update(UserDTO userDTO);
}
