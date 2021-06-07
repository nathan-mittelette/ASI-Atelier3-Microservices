package com.asi.lib.webservices;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "card-micro-service", contextId = "cardWebService", url = "http://${service.name.card:localhost}:5001")
public interface CardWebService {

    @PutMapping("/private/cards/update")
    CardDTO update(CardDTO cardDto);

    @PostMapping("/private/cards/createRandom")
    void createRandomCard(Long userId);

    @GetMapping("/private/cards/{id}")
    CardDTO getById(@PathVariable Long id);

    @GetMapping("/secured/cards/available")
    List<CardDTO> getAllAvailable(Long id);
}
