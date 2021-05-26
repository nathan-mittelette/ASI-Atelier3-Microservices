package com.roommicroservice.roommicroservice.services;

import com.roommicroservice.roommicroservice.models.Player;
import com.roommicroservice.roommicroservice.repositories.PlayerRepository;
import com.roommicroservice.roommicroservice.services.iservices.IPlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends CrudService<Player> implements IPlayerService {
    public PlayerService(PlayerRepository repository) {
        super(repository);
    }
}
