package com.cardmicroservice.cardmicroservice.services.iservices;

import com.cardmicroservice.cardmicroservice.dto.UserDTO;
import com.cardmicroservice.cardmicroservice.models.Card;

import java.util.List;

public interface ICardService extends ICrudService<Card> {
    List<Card> findAllAvailable();
    void buyCard(UserDTO buyerDTO, Long cardId) throws Exception;
    void sellCard(UserDTO seller, Long cardId, Long buyerId) throws Exception;
}
