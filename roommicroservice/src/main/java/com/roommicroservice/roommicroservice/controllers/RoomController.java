package com.roommicroservice.roommicroservice.controllers;

import com.asi.lib.dto.CreateRoomDTO;
import com.asi.lib.dto.JoinRoomDTO;
import com.asi.lib.dto.RoomDTO;
import com.asi.lib.dto.UserDTO;
import com.roommicroservice.roommicroservice.services.iservices.IRoomService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/secured/rooms")
public class RoomController {

    private final IRoomService _roomService;

    public RoomController(IRoomService _roomService) {
        this._roomService = _roomService;
    }

    @PostMapping()
    public RoomDTO createRoom(@AuthenticationPrincipal final UserDTO userDTO, @RequestBody CreateRoomDTO createRoomDto) {
        return _roomService.createRoom(userDTO, createRoomDto);
    }

    @PutMapping()
    public RoomDTO joinRoom(@AuthenticationPrincipal final UserDTO userDTO, @RequestBody JoinRoomDTO joinRoomDto) {
        return _roomService.joinRoom(userDTO, joinRoomDto);
    }

    @GetMapping()
    public List<RoomDTO> getAllCreated() {
        return _roomService.getAllCreated();
    }
}
