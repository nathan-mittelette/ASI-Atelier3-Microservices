package com.roommicroservice.roommicroservice.services.iservices;

import com.asi.lib.dto.RoomDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.iservices.ICrudService;
import com.roommicroservice.roommicroservice.dto.CreateRoomDto;
import com.roommicroservice.roommicroservice.dto.JoinRoomDto;
import com.roommicroservice.roommicroservice.models.Room;

import java.util.List;

public interface IRoomService extends ICrudService<Room> {
    RoomDTO createRoom(UserDTO userDTO, CreateRoomDto createRoomDto);
    RoomDTO joinRoom(UserDTO userDTO, JoinRoomDto joinRoomDto);
    List<RoomDTO> getAllCreated();
}
