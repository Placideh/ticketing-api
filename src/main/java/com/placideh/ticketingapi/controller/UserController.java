package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.entity.User;
import com.placideh.ticketingapi.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Map<String,List<User>>> getUsers()  {
        Map<String,List<User>> message=new HashMap<>();
        List<User> users = userService.getUsers();
        message.put("success",users);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Map<String ,User>> getUserByEmail(@PathVariable String email){
        Map<String,User> message=new HashMap<>();
        User existingUser=userService.getUserByEmail(email);
        message.put("success",existingUser);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Map<String,Integer>> getUsersByStatus(@PathVariable String status){
        Map<String,Integer> message=new HashMap<>();
        message.put("success",userService.getUserByStatus(status));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,User>> getUserById(@PathVariable Integer id){
        Map<String,User> message=new HashMap<>();
        User existingUser=userService.getUserById(id);
        message.put("success",existingUser);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
