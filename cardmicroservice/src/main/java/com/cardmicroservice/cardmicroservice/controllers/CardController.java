package com.cardmicroservice.cardmicroservice.controllers;

import com.cardmicroservice.cardmicroservice.dto.UserDTO;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/cards")
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

    @PutMapping(value = "/buy/{id}")
    public void userBuyCard(@AuthenticationPrincipal final UserDTO userDTO, @PathVariable Long id) throws Exception {
        _cardService.buyCard(userDTO, id);
    }

    @PutMapping(value = "/sell/{id}/user/{id}")
    public Optional<Card> userSellCard(@AuthenticationPrincipal final UserDTO userDTO, @PathVariable Long id) {
        return null;
    }
}
