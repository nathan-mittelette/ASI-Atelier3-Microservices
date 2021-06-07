package com.cardmicroservice.cardmicroservice.services.iservices;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.iservices.ICrudService;
import com.cardmicroservice.cardmicroservice.models.Card;

import java.util.List;

public interface ICardService extends ICrudService<Card> {
    List<CardDTO> findAllAvailable();

    void buyCard(UserDTO buyerDTO, Long cardId);

    void sellCard(UserDTO seller, Long cardId, Long buyerId);

    CardDTO getById(Long id);

    CardDTO createRandomCard(Long userId);
}
