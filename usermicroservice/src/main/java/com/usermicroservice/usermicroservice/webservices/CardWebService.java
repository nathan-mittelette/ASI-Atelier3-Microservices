package com.usermicroservice.usermicroservice.webservices;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="card-micro-service", contextId = "cardWebService", url="http://localhost:5001")
public interface CardWebService {

    @PostMapping("/cards/createRandom")
    void createRandomCard(UserDTO userDTO);
}
