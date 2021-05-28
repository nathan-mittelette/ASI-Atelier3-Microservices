package com.authmicroservice.authmicroservice.services.iservices;

import com.authmicroservice.authmicroservice.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IAuthService {
    String getJWTToken(UserDTO userDTO) throws JsonProcessingException;
}
