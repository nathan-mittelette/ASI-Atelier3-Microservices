package com.cardmicroservice.cardmicroservice.services;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.exceptions.AsiException;
import com.cardmicroservice.cardmicroservice.mapper.CardMapper;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.repositories.CardRepository;
import com.cardmicroservice.cardmicroservice.tools.CardGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    CardRepository cardRepository;

    @Mock
    CardMapper cardMapper;

    @Test
    public void findAllAvailable_returnCardList() {

        // Création d'une fausse liste de card
        List<Card> cardList = Arrays.asList(
                CardGenerator.createCard(1L),
                CardGenerator.createCard(2L),
                CardGenerator.createCard(3L)
        );

        List<CardDTO> cardDTOList = Arrays.asList(
                CardGenerator.createCardDTO(1L),
                CardGenerator.createCardDTO(2L),
                CardGenerator.createCardDTO(3L)
        );

        // On spécifie que lorsque le cardRepository aura la méthode findAllAvailable
        // de call de retourner la liste créé au dessus
        when(cardRepository.findAllAvailable()).thenReturn(cardList);

        // On spécifie que lorsque le cardMapper aura la méthode toDTOList
        // avec la liste de retour du cardRepository de retourner les cardDTO.
        when(cardMapper.toDTOList(cardList)).thenReturn(cardDTOList);

        // On appel la méthode du service qui devrait retourner la liste
        List<CardDTO> cardDTOListResult = cardService.findAllAvailable();

        // On vérifie que le répository a bien été appelé
        verify(cardRepository, times(1)).findAllAvailable();

        // On vérifie que le mapper a bien été appelé
        verify(cardMapper, times(1)).toDTOList(cardList);

        // On vérifie la liste de retour
        assertEquals(3, cardDTOListResult.size());

        for (int i = 0; i < cardDTOList.size(); i++) {
            assertEquals(cardDTOListResult.get(i), cardDTOList.get(i));
        }
    }

    @Test
    public void findByIdDTO_whenExist_ReturnCard() {
        Long id = 1L;
        CardDTO cardDTO = CardGenerator.createCardDTO(id);
        Card card = CardGenerator.createCard(id);

        // On spécifie que lorsque le cardRepository aura la méthode findAllAvailable
        // de call de retourner la liste créé au dessus
        when(cardRepository.findById(id)).thenReturn(Optional.of(card));

        // On spécifie que lorsque le cardMapper aura la méthode toDTO
        // avec la liste de retour du cardRepository de retourner les cardDTO.
        when(cardMapper.toDTO(card)).thenReturn(cardDTO);

        CardDTO cardDTOResult = this.cardService.getById(id);

        // On vérifie que le répository a bien été appelé
        verify(cardRepository, times(1)).findById(id);

        // On vérifie que le mapper a bien été appelé
        verify(cardMapper, times(1)).toDTO(card);

        // On vérifie la liste de retour
        assertEquals(cardDTO, cardDTOResult);
    }

    @Test
    public void findByIdDTO_whenNotExist_ReturnAsiException() {
        Long id = -1L;

        // On spécifie que lorsque le cardRepository aura la méthode findById
        // de call de retourner une optional de null.
        // Cela va trigger une exception disant qu'il n'y a pas de card en base de données.
        when(cardRepository.findById(id)).thenReturn(Optional.empty());

        AsiException exception = assertThrows(AsiException.class, () -> {
            this.cardService.getById(id);
        });

        String expectedMessage = String.format("La card d'id %d n'existe pas en base de données.", id);
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

}
