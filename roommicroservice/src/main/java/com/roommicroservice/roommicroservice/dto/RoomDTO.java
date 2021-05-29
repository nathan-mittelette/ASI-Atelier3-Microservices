package com.roommicroservice.roommicroservice.dto;

import com.roommicroservice.roommicroservice.models.ERoomState;
import com.roommicroservice.roommicroservice.models.Player;

import javax.persistence.*;
import java.io.Serializable;

public class RoomDTO implements Serializable {

    private Long id;
    private Player player1;
    private Player player2;
    private ERoomState state;
}
