package com.roommicroservice.roommicroservice.mapper;

import com.roommicroservice.roommicroservice.dto.PlayerDTO;
import com.roommicroservice.roommicroservice.models.Player;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PlayerMapper {

    PlayerDTO toDTO(Player player);
    Player toBo(PlayerDTO playerDTO);
}
