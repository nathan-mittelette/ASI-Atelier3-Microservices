package com.usermicroservice.usermicroservice.mapper;

import com.usermicroservice.usermicroservice.dto.UserDTO;
import com.usermicroservice.usermicroservice.models.User;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface UserMapper {

    /**
     * Passage de l'objet User à l'objet UserDTO
     *
     * @param source
     * @return
     */
    UserDTO fromUser(User source);

    /**
     * Passage de l'objet UserDTO à l'objet User
     *
     * @param source
     * @return
     */
    User toUser(UserDTO source);
}