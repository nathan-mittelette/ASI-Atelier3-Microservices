package com.authmicroservice.authmicroservice.services.iservices;

import com.asi.lib.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IAuthService {
    String getJWTToken(UserDTO userDTO) throws JsonProcessingException;

    Boolean verifyJWTToken(String token);

    UserDTO getUser(String token) throws Exception;
}
