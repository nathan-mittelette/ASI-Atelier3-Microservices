package com.asi.lib.webservices;

import com.asi.lib.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "card-micro-service", contextId = "cardWebService", url = "http://localhost:5001")
public interface CardWebService {

    @PostMapping("/cards/createRandom")
    void createRandomCard(UserDTO userDTO);
}
