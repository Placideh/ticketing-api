package com.placideh.ticketingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST)
public class InvalidInputException extends  RuntimeException{
    public InvalidInputException(String message) {
        super(message);
    }
}
