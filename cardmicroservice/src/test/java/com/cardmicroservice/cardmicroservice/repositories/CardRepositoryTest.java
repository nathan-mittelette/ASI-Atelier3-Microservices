package com.cardmicroservice.cardmicroservice.repositories;

import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.tools.CardGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CardRepositoryTest {

    @Autowired
    public CardRepository cardRepository;

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void findById_withExistingId() {
        Long id = 1001L;
        Optional<Card> cardOptional = this.cardRepository.findById(id);
        assertTrue(cardOptional.isPresent());

        Card card = cardOptional.get();

        assertEquals(card.getId(), id);
        assertEquals(card.getName(), String.format("card %d", id));
        assertEquals(card.getDescription(), String.format("description %d", id));
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void findById_withNonExistingId() {
        Long id = -1L;
        Optional<Card> cardOptional = this.cardRepository.findById(id);
        assertTrue(cardOptional.isEmpty());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void findAllAvailable_shouldReturnCardWithEmptyUser() {
        List<Card> cards = this.cardRepository.findAllAvailable();
        assertEquals(cards.size(), 3);

        List<Long> ids = Arrays.asList(1003L, 1004L, 1005L);

        for (int i = 0; i < cards.size(); i++) {
            assertEquals(cards.get(i).getId(), ids.get(i));
            assertEquals(cards.get(i).getName(), String.format("card %d", ids.get(i)));
            assertEquals(cards.get(i).getDescription(), String.format("description %d", ids.get(i)));
        }
    }

    @Test
    public void create_delete_card() {
        Long id = 2000L;
        Card card = CardGenerator.createCard(id);
        // Sauvegarde de l'objet.
        Card savedCard = this.cardRepository.save(card);
        card.setId(savedCard.getId());
        assertEquals(card, savedCard);
        assertTrue(this.cardRepository.findById(card.getId()).isPresent());


        // Suppression de la card
        this.cardRepository.delete(savedCard);
        assertTrue(this.cardRepository.findById(id).isEmpty());
    }

    @Test
    @SqlGroup({
            @Sql(scripts = "classpath:scripts/create-cards.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(scripts = "classpath:scripts/remove-cards.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void update_cards() {
        Random random = new Random();
        Long id = 1003L;
        Card card = this.cardRepository.findById(id).get();

        String oldFamily = card.getFamily();
        String randomFamily = String.format("Family %d", random.nextInt());

        card.setFamily(randomFamily);
        this.cardRepository.save(card);
        card = this.cardRepository.findById(id).get();

        assertEquals(card.getFamily(), randomFamily);
        card.setFamily(oldFamily);
        this.cardRepository.save(card);
    }
}
