package com.asi.lib.webservices;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "card-micro-service", contextId = "cardWebService", url = "http://localhost:5001")
public interface CardWebService {

    @PostMapping("/cards/createRandom")
    void createRandomCard(UserDTO userDTO);

    @GetMapping("/private/{id}")
    CardDTO getById(Long id);
}
