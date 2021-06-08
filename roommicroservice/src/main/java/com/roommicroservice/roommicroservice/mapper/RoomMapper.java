package com.roommicroservice.roommicroservice.mapper;

import com.asi.lib.dto.RoomDTO;
import com.roommicroservice.roommicroservice.models.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {PlayerMapper.class})
@Component
public interface RoomMapper {

    RoomDTO toDTO(Room source);

    Room toBo(RoomDTO source);

    List<RoomDTO> toDTOList(List<Room> sources);
}
