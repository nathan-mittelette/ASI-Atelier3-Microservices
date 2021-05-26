package com.cardmicroservice.cardmicroservice.mapper;

import com.cardmicroservice.cardmicroservice.dto.UserDTO;
import com.cardmicroservice.cardmicroservice.models.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO fromUser(User source) {
        if ( source == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( source.getId() );
        userDTO.setLastname( source.getLastname() );
        userDTO.setFirstname( source.getFirstname() );
        userDTO.setEmail( source.getEmail() );
        userDTO.setMoney( source.getMoney() );

        return userDTO;
    }

    @Override
    public User toUser(UserDTO source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( source.getId() );
        user.setLastname( source.getLastname() );
        user.setFirstname( source.getFirstname() );
        user.setEmail( source.getEmail() );
        user.setMoney( source.getMoney() );

        return user;
    }
}
