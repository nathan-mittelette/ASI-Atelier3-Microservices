package com.cardmicroservice.cardmicroservice.controllers;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private final ICardService _cardService;

    public CardController(ICardService cardService) {
        _cardService = cardService;
    }

    @GetMapping(value = "/secured/cards/mine")
    public List<CardDTO> getCurrentUserCards(@AuthenticationPrincipal UserDTO userDTO) {
        return _cardService.getCurrentUserCards(userDTO);
    }

    @GetMapping(value = "/secured/cards/available")
    public List<CardDTO> getAvailable() {
        return _cardService.findAllAvailable();
    }

    @PostMapping(value = "/private/cards/createRandom")
    public CardDTO createRandom(@RequestBody Long userId) {
        return _cardService.createRandomCard(userId);
    }

    @PutMapping(value = "/private/cards/update")
    public CardDTO update(@RequestBody CardDTO cardDTO) {
        return _cardService.update(cardDTO);
    }

    @GetMapping(value = "/private/cards/{id}")
    public CardDTO getById(@PathVariable Long id) {
        return _cardService.getById(id);
    }
}
