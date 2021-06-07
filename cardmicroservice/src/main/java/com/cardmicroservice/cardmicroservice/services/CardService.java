package com.cardmicroservice.cardmicroservice.services;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.exceptions.AsiException;
import com.asi.lib.services.CrudService;
import com.asi.lib.webservices.UserWebService;
import com.cardmicroservice.cardmicroservice.mapper.CardMapper;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.repositories.CardRepository;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CardService extends CrudService<Card> implements ICardService {

    private final UserWebService _userWebService;
    private final CardRepository _cardRepository;
    private final CardMapper _cardMapper;

    public CardService(CardRepository repository, CardMapper cardMapper, UserWebService userWebService) {
        super(repository);
        _cardRepository = repository;
        _cardMapper = cardMapper;
        _userWebService = userWebService;
    }

    public CardDTO getById(Long id) {
        Card card = _repository.findById(id).orElseThrow(() -> new AsiException(String.format("La card d'id %d n'existe pas en base de données.", id)));
        return cardMapper.toDTO(card);
    }

    public List<CardDTO> getByUserId(Long userId) {
        UserDTO user = _userWebService.getById(userId);

        if (user == null) {
            throw new RuntimeException("Invalid user id");
        }

        return _cardRepository.getByUserId(userId).stream().map(c -> _cardMapper.toDTO(c)).collect(Collectors.toList());
    }

    public CardDTO createRandomCard(Long userId) {
        Card card = new Card();
        card.setName(UUID.randomUUID().toString().replace("-", ""));
        card.setDescription(UUID.randomUUID().toString().replace("-", ""));
        card.setFamily(UUID.randomUUID().toString().replace("-", ""));
        card.setAffinity(UUID.randomUUID().toString().replace("-", ""));
        card.setHp(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 100 + 1)));
        card.setEnergy(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 100 + 1)));
        card.setAttack(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 100 + 1)));
        card.setDefense(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 100 + 1)));
        card.setPrice(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 100 + 1)));
        card.setUserId(userId);

        card = this.insertOrUpdate(card);

        return this.cardMapper.toDTO(card);
    }

    @Override
    public List<CardDTO> findAllAvailable() {
        return this.cardMapper.toDTOList(_cardRepository.findAllAvailable());
    }

    public CardDTO update(CardDTO cardDTO) {
        Card card = cardMapper.toBo(cardDTO);
        card = this.insertOrUpdate(card);
        return cardMapper.toDTO(card);
    }
}
