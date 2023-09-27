package com.example.userrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InappropriateDateException extends RuntimeException {

    public InappropriateDateException(String message) {
        super(message);
    }
}
