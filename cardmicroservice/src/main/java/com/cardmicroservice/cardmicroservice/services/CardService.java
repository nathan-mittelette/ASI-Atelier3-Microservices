package com.cardmicroservice.cardmicroservice.services;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.CrudService;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.repositories.CardRepository;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService extends CrudService<Card> implements ICardService {

    private CardRepository _cardRepository;

    public CardService(CardRepository repository) {
        super(repository);
        _cardRepository = repository;
    }

    @Override
    public List<Card> findAllAvailable() {
        return _cardRepository.findAllAvailable();
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
