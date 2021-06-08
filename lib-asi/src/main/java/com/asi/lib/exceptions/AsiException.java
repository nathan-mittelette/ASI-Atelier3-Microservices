package com.asi.lib.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AsiException extends RuntimeException {

    public AsiException(String message) {
        super(message);
    }
}
