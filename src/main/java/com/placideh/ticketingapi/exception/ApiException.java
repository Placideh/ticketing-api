package com.placideh.ticketingapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiException {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;

}
