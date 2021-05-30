package com.cardmicroservice.cardmicroservice.controllers;

import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/secured/cards")
public class CardController {
    private final ICardService _cardService;

    public CardController(ICardService cardService) {
        _cardService = cardService;
    }

    @GetMapping(value = "{id}")
    public Optional<Card> getCardById(@PathVariable Long id) {
        return _cardService.findById(id);
    }

    @GetMapping(value = "/available")
    public List<Card> getAvailableCards() {
        return _cardService.findAllAvailable();
    }
}
