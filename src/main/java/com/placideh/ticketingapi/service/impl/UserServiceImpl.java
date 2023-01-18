package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.entity.Status;
import com.placideh.ticketingapi.entity.User;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.UserRepo;
import com.placideh.ticketingapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email.trim())
                .orElseThrow(()-> new NotFoundException("email :"+email+ " not found"));
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("userId: "+id+" not found"));
    }

    @Override
    public Integer getUserByStatus(String status) {
       return userRepo.countByStatus(Status.valueOf(status.toUpperCase()));
    }
}
