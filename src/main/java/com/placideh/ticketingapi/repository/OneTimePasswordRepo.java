package com.placideh.ticketingapi.repository;


import com.placideh.ticketingapi.entity.OneTimePassword;
import com.placideh.ticketingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OneTimePasswordRepo extends JpaRepository<OneTimePassword,String> {


    Optional<OneTimePassword> findByOtp(String otp);
    Optional<OneTimePassword> findByUser(User user);


}
