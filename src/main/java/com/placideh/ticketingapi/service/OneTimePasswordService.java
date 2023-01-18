package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.entity.OneTimePassword;
import com.placideh.ticketingapi.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface OneTimePasswordService {
    OneTimePassword getOtp(String otp);
    void deleteOtp(String otp);
    void createOtp(OneTimePassword otp);
    OneTimePassword getOtpByUser(User user);
}
