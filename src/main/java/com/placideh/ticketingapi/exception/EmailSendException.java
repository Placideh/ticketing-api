package com.placideh.ticketingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.UnknownHostException;
@ResponseStatus(code= HttpStatus.REQUEST_TIMEOUT)
public class EmailSendException extends UnknownHostException {

    public EmailSendException(String message) {
        super(message);
    }

}
