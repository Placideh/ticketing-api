package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.Dto.UserDto;
import com.placideh.ticketingapi.entity.PasswordReset;
import com.placideh.ticketingapi.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AuthService {
    User createUserAccount(UserDto user) throws Exception;
    boolean verifyEmail(String otp);
    Map<String,String> login(String email, String password);

}
