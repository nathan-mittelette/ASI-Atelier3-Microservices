package com.roommicroservice.roommicroservice.services.iservices;

import com.asi.lib.dto.RoomDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.iservices.ICrudService;
import com.asi.lib.dto.CreateRoomDTO;
import com.asi.lib.dto.JoinRoomDTO;
import com.roommicroservice.roommicroservice.models.Room;

import java.util.List;

public interface IRoomService extends ICrudService<Room> {
    RoomDTO createRoom(UserDTO userDTO, CreateRoomDTO createRoomDto);
    RoomDTO joinRoom(UserDTO userDTO, JoinRoomDTO joinRoomDto);
    List<RoomDTO> getAllCreated();
}
