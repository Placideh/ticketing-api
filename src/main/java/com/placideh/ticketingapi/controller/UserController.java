package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.entity.User;
import com.placideh.ticketingapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/users")
@Api(value = "User endpoints")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    @ApiOperation(value="Returns List of user")
    public ResponseEntity<Map<String,List<User>>> getUsers()  {
        Map<String,List<User>> message=new HashMap<>();
        List<User> users = userService.getUsers();
        message.put("success",users);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    @ApiOperation(value = "get a user by email")
    public ResponseEntity<Map<String ,User>> getUserByEmail(@PathVariable String email){
        Map<String,User> message=new HashMap<>();
        User existingUser=userService.getUserByEmail(email);
        message.put("success",existingUser);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

//    TODO ANALYZE THIS ENDPOINT
    @GetMapping("/status/{status}")
    @ApiOperation(value="get user by status")
    public ResponseEntity<Map<String,Integer>> getUsersByStatus(@PathVariable String status){
        Map<String,Integer> message=new HashMap<>();
        message.put("success",userService.getUserByStatus(status));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get user by Id")
    public ResponseEntity<Map<String,User>> getUserById(@PathVariable Integer id){
        Map<String,User> message=new HashMap<>();
        User existingUser=userService.getUserById(id);
        message.put("success",existingUser);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
