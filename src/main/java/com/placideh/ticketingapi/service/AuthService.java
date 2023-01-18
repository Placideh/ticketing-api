package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.Dto.UserDto;
import com.placideh.ticketingapi.entity.PasswordReset;
import com.placideh.ticketingapi.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User createUserAccount(UserDto user) throws Exception;
    boolean verifyEmail(String otp);
    Integer login(String email,String password);

}
