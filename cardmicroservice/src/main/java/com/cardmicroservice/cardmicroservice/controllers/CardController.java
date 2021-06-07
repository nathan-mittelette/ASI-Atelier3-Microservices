package com.cardmicroservice.cardmicroservice.controllers;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CardController {
    private final ICardService _cardService;

    public CardController(ICardService cardService) {
        _cardService = cardService;
    }

    @GetMapping(value = "/private/cards/{id}")
    public CardDTO getCardById(@PathVariable Long id) throws Exception {
        return _cardService.getById(id);
    }

    @GetMapping(value = "/secured/cards/available")
    public List<CardDTO> getAvailableCards() {
        return _cardService.findAllAvailable();
    }

    @PostMapping(value = "/private/cards/createRandom")
    public CardDTO createRandomCard(@RequestBody Long userId) {
        return _cardService.createRandomCard(userId);
    }
}
