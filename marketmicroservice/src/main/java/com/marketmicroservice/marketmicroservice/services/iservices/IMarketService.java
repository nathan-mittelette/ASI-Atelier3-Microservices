package com.marketmicroservice.marketmicroservice.services.iservices;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;

public interface IMarketService {
    void buyCard(UserDTO buyerDTO, Long cardId);
    void sellCard(UserDTO userDTO, Long cardId);
}
