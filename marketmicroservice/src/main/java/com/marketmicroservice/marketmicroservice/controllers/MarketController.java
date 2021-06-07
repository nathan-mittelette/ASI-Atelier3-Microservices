package com.marketmicroservice.marketmicroservice.controllers;

import com.marketmicroservice.marketmicroservice.services.iservices.IMarketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/secured/market")
public class MarketController {
    private final IMarketService _marketService;

    public MarketController(IMarketService marketService) {
        _marketService = marketService;
    }
}
