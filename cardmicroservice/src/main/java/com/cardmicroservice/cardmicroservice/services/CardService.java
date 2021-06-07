package com.cardmicroservice.cardmicroservice.services;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.CrudService;
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

    public CardService(CardRepository repository, CardMapper cardMapper) {
        super(repository);
        _cardRepository = repository;
        _cardMapper = cardMapper;
    }

    public CardDTO getById(Long id) throws Exception {
        Card card = _repository.findById(id).orElseThrow(() -> new Exception("Card not found."));
        return _cardMapper.toDTO(card);
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
        card.setUserid(userId);

        this.insertOrUpdate(card);

        return _cardMapper.toDTO(card);
    }

    @Override
    public List<CardDTO> findAllAvailable() {
        return _cardRepository.findAllAvailable().stream().map(r -> _cardMapper.toDTO(r)).collect(Collectors.toList());
    }

    @Override
    public void buyCard(UserDTO buyerDTO, Long cardId) throws Exception {
        Card card = _cardRepository.findById(cardId).get();
        //User buyer = this.userMapper.toUser(buyerDTO);
        // TODO faire les appels via le webService
        //User seller = card.getUser();

        //this.sellOperation(buyer, seller, card);
    }

    @Override
    public void sellCard(UserDTO sellerDTO, Long cardId, Long buyerId) throws Exception {
        Card card = _cardRepository.findById(cardId).get();
        //User seller = this.userMapper.toUser(sellerDTO);
        //User buyer = this.userService.findById(buyerId).get();

        //this.sellOperation(buyer, seller, card);
    }

    private void sellOperation(UserDTO seller, UserDTO buyer, Card card) throws Exception {
        if (card == null || seller == null || buyer == null) {
            throw new Exception("Wrong operation.");
        }

        if (buyer.getMoney() < card.getPrice()) {
            throw new Exception("Not enough money.");
        }

        // TODO adapter avec les microservices
        //card.setUser(buyer);
        buyer.setMoney(buyer.getMoney() - card.getPrice());

        // we can buy available cards = no seller
        if (seller != null) {
            seller.setMoney(seller.getMoney() + card.getPrice());
        }

        this.insertOrUpdate(card);
        // this.userService.insertOrUpdate(seller);
        // this.userService.insertOrUpdate(buyer);
    }
}
