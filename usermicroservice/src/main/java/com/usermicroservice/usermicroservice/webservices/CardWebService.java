package com.usermicroservice.usermicroservice.webservices;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public interface CardWebService {

    @PostMapping("/cards/createRandom")
    void createRandomCard(UserDTO userDTO);
}
