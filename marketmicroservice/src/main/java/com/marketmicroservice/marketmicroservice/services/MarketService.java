package com.marketmicroservice.marketmicroservice.services;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.webservices.CardWebService;
import com.asi.lib.webservices.UserWebService;
import com.marketmicroservice.marketmicroservice.services.iservices.IMarketService;
import org.springframework.stereotype.Service;

@Service
public class MarketService implements IMarketService {

    private CardWebService _cardWebService;
    private UserWebService _userWebService;

    private MarketService(CardWebService cardWebService, UserWebService userWebService) {
        this._cardWebService = cardWebService;
        this._userWebService = userWebService;
    }

    public void buyCard(UserDTO userDTO, Long cardId) {
        CardDTO card = _cardWebService.getById(cardId);

        if (card.getUserId() == null) {
            throw new RuntimeException("Card has no owner");
        }

        UserDTO currentUser = _userWebService.getById(card.getUserId());
        UserDTO newUser = _userWebService.getById(userDTO.getId());

        if (card.getUserId() != currentUser.getId()) {
            throw new RuntimeException("Invalid owner");
        }

        if (!card.getAvailable()) {
            throw new RuntimeException("Card not available for selling");
        }

        if (newUser.getMoney() < card.getPrice()) {
            throw new RuntimeException("Not enough money");
        }

        currentUser.setMoney(currentUser.getMoney() + card.getPrice());
        _userWebService.update(currentUser);

        newUser.setMoney(newUser.getMoney() - card.getPrice());
        _userWebService.update(newUser);

        card.setAvailable(false);
        card.setUserId(newUser.getId());
        _cardWebService.update(card);
    }

    public void sellCard(UserDTO userDTO, Long cardId) {
        CardDTO card = _cardWebService.getById(cardId);

        if (card.getUserId() != userDTO.getId()) {
            throw new RuntimeException("Invalid owner");
        }

        card.setAvailable(true);
        _cardWebService.update(card);
    }
}
