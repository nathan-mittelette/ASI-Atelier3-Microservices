package com.cardmicroservice.cardmicroservice.services;

import com.cardmicroservice.cardmicroservice.dto.UserDTO;
import com.cardmicroservice.cardmicroservice.mapper.UserMapper;
import com.cardmicroservice.cardmicroservice.models.Card;
import com.cardmicroservice.cardmicroservice.models.User;
import com.cardmicroservice.cardmicroservice.repositories.CardRepository;
import com.cardmicroservice.cardmicroservice.services.iservices.ICardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService extends CrudService<Card> implements ICardService {

    private CardRepository _cardRepository;
    private final UserMapper userMapper;

    public CardService(CardRepository repository, UserMapper userMapper) {
        super(repository);
        _cardRepository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public List<Card> findAllAvailable() {
        return _cardRepository.findAllAvailable();
    }

    @Override
    public void buyCard(UserDTO buyerDTO, Long cardId) throws Exception {
        Card card = _cardRepository.findById(cardId).get();
        User buyer = this.userMapper.toUser(buyerDTO);
        User seller = card.getUser();

        this.sellOperation(buyer, seller, card);
    }

    @Override
    public void sellCard(UserDTO sellerDTO, Long cardId, Long buyerId) throws Exception {
        Card card = _cardRepository.findById(cardId).get();
        User seller = this.userMapper.toUser(sellerDTO);
        //User buyer = this.userService.findById(buyerId).get();

        //this.sellOperation(buyer, seller, card);
    }

    private void sellOperation(User buyer, User seller, Card card) throws Exception {
        if (card == null || seller == null || buyer == null){
            throw new Exception("Wrong operation.");
        }

        if (buyer.getMoney() < card.getPrice()){
            throw new Exception("Not enough money.");
        }

        card.setUser(buyer);
        buyer.setMoney(buyer.getMoney() - card.getPrice());

        // we can buy available cards = no seller
        if (seller != null) {
            seller.setMoney(seller.getMoney()+card.getPrice());
        }

        this.insertOrUpdate(card);
        // this.userService.insertOrUpdate(seller);
        // this.userService.insertOrUpdate(buyer);
    }
}
