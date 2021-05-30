package com.roommicroservice.roommicroservice.controllers;

import com.asi.lib.dto.UserDTO;
import com.asi.lib.dto.RoomDTO;
import com.roommicroservice.roommicroservice.services.iservices.IRoomService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/secure/rooms")
public class RoomController {

    private final IRoomService _roomService;

    public RoomController(IRoomService _roomService) {
        this._roomService = _roomService;
    }

    @PostMapping(value = "/{bet}")
    public RoomDTO createRoom(@AuthenticationPrincipal final UserDTO userDTO, @PathVariable Long bet) {
        return _roomService.createRoom(userDTO, bet);
    }
}
