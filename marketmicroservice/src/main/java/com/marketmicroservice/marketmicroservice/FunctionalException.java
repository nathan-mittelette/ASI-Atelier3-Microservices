package com.marketmicroservice.marketmicroservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FunctionalException extends RuntimeException {
    public FunctionalException(String message) {
        super(message);
    }
}
