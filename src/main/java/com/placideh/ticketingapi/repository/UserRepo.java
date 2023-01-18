package com.placideh.ticketingapi.repository;

import com.placideh.ticketingapi.entity.Status;
import com.placideh.ticketingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Integer countByStatus(Status status);
}
