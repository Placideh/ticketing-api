package com.placideh.ticketingapi.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request){
        return new ResponseEntity<>(new ApiException(ex.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    public ResponseEntity<Object> handleAlreadyExist(AlreadyExistException ex, WebRequest request){
        return new ResponseEntity<>(new ApiException(ex.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now()),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleInvalidInput(ConstraintViolationException ex, WebRequest request){
        return new ResponseEntity<>(new ApiException(ex.getConstraintViolations().toArray()[0].toString().substring(45,80), HttpStatus.BAD_REQUEST, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {EmailSendException.class})
    public ResponseEntity<Object> handleEmailSend(EmailSendException ex, WebRequest request){
        return new ResponseEntity<>(new ApiException(ex.getMessage(), HttpStatus.REQUEST_TIMEOUT, LocalDateTime.now()),HttpStatus.REQUEST_TIMEOUT);
    }
    @ExceptionHandler(value = {CustomInputException.class})
    public ResponseEntity<Object> handleCustomInput(CustomInputException ex, WebRequest request){
        return new ResponseEntity<>(new ApiException(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}