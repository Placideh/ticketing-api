package com.placideh.ticketingapi.controller;


import com.placideh.ticketingapi.Dto.LoginDto;
import com.placideh.ticketingapi.Dto.UserDto;
import com.placideh.ticketingapi.entity.User;
import com.placideh.ticketingapi.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/auth/user")
@Api(value = "Auth endpoints")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping( value = "/signup")
    @ApiOperation(value = "register a user")
    public ResponseEntity<Map<String,User>> registerUser(UserDto user ) {
        Map<String ,User> message=new HashMap<>();
        User newUser;
        try {
            newUser = authService.createUserAccount(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        message.put("success",newUser);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/verify/{otp}")
    @ApiOperation(value = "verify user email")
    public ResponseEntity<Map<String,Boolean>> verifyUserEmail(@PathVariable String otp){
        Map<String ,Boolean> message=new HashMap<>();
        Boolean result=authService.verifyEmail(otp);
        message.put("success",result);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // TODO REWORK ON LOGIN TO SIGNUP WITH USING JWT TOKENS
    @PostMapping(value="/login")
    @ApiOperation(value = "user login")
    public ResponseEntity<Map<String,String>> login(@Valid LoginDto loginCredentials){
        Map<String,String>message=new HashMap<>();

        Integer userId=authService.login(loginCredentials.getEmail(),loginCredentials.getPassword());
        if (userId==0) {
            message.put("error","invalid email/password");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.put("success",userId.toString());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

}
