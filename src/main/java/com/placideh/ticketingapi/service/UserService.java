package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.entity.User;
import com.placideh.ticketingapi.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    List<User> getUsers();
    User getUserByEmail(String email);

    User getUserById(Integer id);

    Integer getUserByStatus(String status);
}
