package com.placideh.ticketingapi.repository;

import com.placideh.ticketingapi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<Ticket,Integer> {
}
