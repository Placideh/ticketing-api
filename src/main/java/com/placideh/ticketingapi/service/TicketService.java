package com.placideh.ticketingapi.service;


import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    Ticket generateTicket(String userEmail, Integer tripId);
    Ticket getTicketById(Integer ticketId);

    List<Ticket> getGeneratedTickets();
}
