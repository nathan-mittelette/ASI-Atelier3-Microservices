package com.usermicroservice.usermicroservice.webservices;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public interface AuthWebService {

    @GetMapping("/cards/")
    String getJWTToken(@PathVariable UserDTO userDTO);
}
