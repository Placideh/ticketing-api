package com.placideh.ticketingapi.service;


import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Ticket;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {

    Ticket generateTicket(String userEmail, String source, String destination, ScheduleDto scheduleDto);
    Ticket getTicketById(Integer ticketId);
}
