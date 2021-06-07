package com.cardmicroservice.cardmicroservice.services;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.CrudService;
import com.asi.lib.webservices.UserWebService;
import com.cardmicroservice.cardmicroservice.mapper.CardMapper;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.repositories.CardRepository;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.stereotype.Service;
import java.util.UUID;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CardService extends CrudService<Card> implements ICardService {

    private CardRepository _cardRepository;
    private CardMapper _cardMapper;
    private UserWebService _userWebService;

    public CardService(CardRepository repository, CardMapper cardMapper, UserWebService _userWebService) {
        super(repository);
        _cardRepository = repository;
        _cardMapper = cardMapper;
        _userWebService = _userWebService;
    }

    public CardDTO getById(Long id) {
        Card card = _repository.findById(id).orElseThrow(() -> new RuntimeException("Card not found."));
        return _cardMapper.toDTO(card);
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

        this.insertOrUpdate(card);

        return _cardMapper.toDTO(card);
    }

    @Override
    public List<CardDTO> findAllAvailable() {
        return _cardRepository.findAllAvailable().stream().map(r -> _cardMapper.toDTO(r)).collect(Collectors.toList());
    }

    public CardDTO update(CardDTO cardDTO) {
        Card card = _cardMapper.toBo(cardDTO);
        this.insertOrUpdate(card);
        return _cardMapper.toDTO(card);
    }
}
