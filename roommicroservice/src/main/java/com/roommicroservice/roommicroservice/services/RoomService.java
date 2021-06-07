package com.roommicroservice.roommicroservice.services;

import com.asi.lib.dto.RoomDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.enums.ERoomState;
import com.asi.lib.services.CrudService;
import com.roommicroservice.roommicroservice.mapper.RoomMapper;
import com.roommicroservice.roommicroservice.models.Player;
import com.roommicroservice.roommicroservice.models.Room;
import com.roommicroservice.roommicroservice.repositories.PlayerRepository;
import com.roommicroservice.roommicroservice.repositories.RoomRepository;
import com.roommicroservice.roommicroservice.services.iservices.IRoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends CrudService<Room> implements IRoomService {

    private PlayerRepository _playerRepository;
    private RoomMapper _roomMapper;

    public RoomService(RoomRepository repository, PlayerRepository playerRepository, RoomMapper roomMapper) {
        super(repository);
        _playerRepository = playerRepository;
        _roomMapper = roomMapper;
    }

    @Override
    public RoomDTO createRoom(UserDTO userDTO, Long bet) {
        Player player1 = new Player();
        player1.setUserId(userDTO.getId());

        _playerRepository.save(player1);

        Room room = new Room();
        room.setPlayer1(player1);
        room.setBet(bet);
        room.setState(ERoomState.CREATED);

        room = _repository.save(room);

        return _roomMapper.toDTO(room);
    }
}
