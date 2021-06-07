package com.asi.lib.webservices;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "card-micro-service", contextId = "cardWebService", url = "http://localhost:5001")
public interface CardWebService {

    @PostMapping("/private/cards/createRandom")
    void createRandomCard(Long userId);

    @GetMapping("/private/cards/{id}")
    CardDTO getById(@PathVariable Long id);

    @GetMapping("/secured/cards/available")
    List<CardDTO> getAllAvailable(Long id);
}
