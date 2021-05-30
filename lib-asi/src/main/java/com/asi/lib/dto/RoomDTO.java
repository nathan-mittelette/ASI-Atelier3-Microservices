package com.asi.lib.dto;

import com.asi.lib.enums.ERoomState;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDTO implements Serializable {

    private Long id;
    private PlayerDTO player1;
    private PlayerDTO player2;
    private ERoomState state;
    private Long bet;

}
