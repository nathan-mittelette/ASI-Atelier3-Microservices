package com.marketmicroservice.marketmicroservice.controllers;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.marketmicroservice.marketmicroservice.services.iservices.IMarketService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/secured/market")
public class MarketController {
    private final IMarketService _marketService;

    public MarketController(IMarketService marketService) {
        _marketService = marketService;
    }

    @PutMapping(value = "/sell/{cardId}")
    public void sell(@AuthenticationPrincipal final UserDTO userDTO, @PathVariable Long cardId) {
        _marketService.sellCard(userDTO, cardId);
    }

    @PutMapping(value = "/buy/{cardId}")
    public void buyCard(@AuthenticationPrincipal final UserDTO userDTO, @PathVariable Long cardId) {
        _marketService.buyCard(userDTO, cardId);
    }
}
