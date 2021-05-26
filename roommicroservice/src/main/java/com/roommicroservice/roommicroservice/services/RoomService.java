package com.roommicroservice.roommicroservice.services;

import com.roommicroservice.roommicroservice.models.Room;
import com.roommicroservice.roommicroservice.repositories.RoomRepository;
import com.roommicroservice.roommicroservice.services.iservices.IRoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends CrudService<Room> implements IRoomService {

    private RoomRepository _cardRepository;
    private final PlayerService playerService;

    public RoomService(RoomRepository repository, PlayerService playerService) {
        super(repository);
        _cardRepository = repository;
        this.playerService = playerService;
    }
}
