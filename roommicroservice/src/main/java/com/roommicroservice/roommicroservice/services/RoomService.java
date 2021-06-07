package com.roommicroservice.roommicroservice.services;

import com.asi.lib.dto.CardDTO;
import com.asi.lib.dto.RoomDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.enums.ERoomState;
import com.asi.lib.services.CrudService;
import com.asi.lib.webservices.CardWebService;
import com.roommicroservice.roommicroservice.dto.CreateRoomDto;
import com.roommicroservice.roommicroservice.dto.JoinRoomDto;
import com.roommicroservice.roommicroservice.mapper.RoomMapper;
import com.roommicroservice.roommicroservice.models.Player;
import com.roommicroservice.roommicroservice.models.Room;
import com.roommicroservice.roommicroservice.repositories.PlayerRepository;
import com.roommicroservice.roommicroservice.repositories.RoomRepository;
import com.roommicroservice.roommicroservice.services.iservices.IRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService extends CrudService<Room> implements IRoomService {

    private PlayerRepository _playerRepository;
    private RoomRepository _roomRepository;
    private RoomMapper _roomMapper;
    private final CardWebService _cardWebService;

    public RoomService(RoomRepository repository, PlayerRepository playerRepository, RoomMapper roomMapper, CardWebService cardWebService) {
        super(repository);
        _roomRepository = repository;
        _playerRepository = playerRepository;
        _roomMapper = roomMapper;
        _cardWebService = cardWebService;
    }

    @Override
    public RoomDTO createRoom(UserDTO userDTO, CreateRoomDto createRoomDto) {
        Player player = VerifyAndExtractPlayer(userDTO.getId(), createRoomDto.getCardId());

        Room room = new Room();
        room.setPlayer1(player);
        room.setBet(createRoomDto.getBet());
        room.setState(ERoomState.CREATED);

        _repository.save(room);

        return _roomMapper.toDTO(room);
    }

    public RoomDTO joinRoom(UserDTO userDTO, JoinRoomDto joinRoomDto) {
        Room room = _repository.findById(joinRoomDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

        Player player = VerifyAndExtractPlayer(userDTO.getId(), joinRoomDto.getCardId());

        room.setPlayer2(player);
        room.setState(ERoomState.JOINED);

        _repository.save(room);

        return _roomMapper.toDTO(room);
    }

    public List<RoomDTO> getAllCreated() {
        List<Room> rooms = _roomRepository.getAllCreated();
        return rooms.stream().map(r -> _roomMapper.toDTO(r)).collect(Collectors.toList());
    }

    private Player VerifyAndExtractPlayer(Long userId, Long cardId) {
        CardDTO card = _cardWebService.getById(cardId);

        if (card.getUserId() == null || !card.getUserId().equals(userId)) {
            throw new RuntimeException("Invalid owner");
        }

        Player player = new Player();
        player.setUserId(userId);
        player.setCardId(card.getId());

        _playerRepository.save(player);

        return player;
    }
}