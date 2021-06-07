package com.roommicroservice.roommicroservice.services.iservices;

import com.asi.lib.dto.RoomDTO;
import com.asi.lib.dto.UserDTO;
import com.asi.lib.services.iservices.ICrudService;
import com.roommicroservice.roommicroservice.models.Room;

public interface IRoomService extends ICrudService<Room> {
    RoomDTO createRoom(UserDTO userDTO, Long bet);
}
