package com.cardmicroservice.cardmicroservice.services.iservices;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.exceptions.AsiException;
import com.asi.lib.services.iservices.ICrudService;
import com.cardmicroservice.cardmicroservice.models.Card;

import java.util.List;

public interface ICardService extends ICrudService<Card> {
    List<CardDTO> findAllAvailable();

    List<CardDTO> getCurrentUserCards(UserDTO userDTO);

    CardDTO getById(Long id);

    CardDTO createRandomCard(Long userId);

    CardDTO update(CardDTO cardDTO);
}
