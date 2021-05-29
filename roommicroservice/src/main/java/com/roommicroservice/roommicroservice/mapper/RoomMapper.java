package com.roommicroservice.roommicroservice.mapper;

import com.roommicroservice.roommicroservice.dto.PlayerDTO;
import com.roommicroservice.roommicroservice.dto.RoomDTO;
import com.roommicroservice.roommicroservice.models.Player;
import com.roommicroservice.roommicroservice.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface RoomMapper {

    RoomDTO toDTO(Room room);
    Room toBo(PlayerDTO playerDTO);
}