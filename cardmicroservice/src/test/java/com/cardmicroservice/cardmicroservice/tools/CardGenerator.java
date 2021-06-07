package com.cardmicroservice.cardmicroservice.tools;

import com.asi.lib.dto.CardDTO;
import com.cardmicroservice.cardmicroservice.models.Card;

public class CardGenerator {

    public static CardDTO createCardDTO(Long id) {
        return new CardDTO(id,
                String.format("Card %d", id),
                String.format("Description %d", id),
                String.format("Image %d", id),
                String.format("Family %d", id),
                String.format("Affinity %d", id),
                id * 100,
                id * 100,
                id * 100,
                id * 100,
                id * 100,
                false,
                1001L
        );
    }

    public static  Card createCard(Long id) {
        return new Card(id,
                String.format("Card %d", id),
                String.format("Description %d", id),
                String.format("Image %d", id),
                String.format("Family %d", id),
                String.format("Affinity %d", id),
                id * 100,
                id * 100,
                id * 100,
                id * 100,
                id * 100,
                false,
                1001L
        );
    }
}
