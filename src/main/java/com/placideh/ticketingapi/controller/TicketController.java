package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Schedule;
import com.placideh.ticketingapi.entity.Ticket;
import com.placideh.ticketingapi.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/tickets")
@Api(value = "Ticket endpoints")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    @ApiOperation(value="generate Ticket")
    public ResponseEntity<Map<String, Ticket>> generateTicket(String userEmail, String source,
    String destination, ScheduleDto scheduleDto){

        Map<String,Ticket> message=new HashMap<>();
        Ticket generatedTicket= ticketService.generateTicket(userEmail,source,destination,scheduleDto);
        message.put("success",generatedTicket);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/{ticketId}")
    @ApiOperation(value="get Ticket")
    public ResponseEntity<Map<String, Ticket>> getTicketById(Integer tickedId){

        Map<String,Ticket> message=new HashMap<>();
        Ticket ticket= ticketService.getTicketById(tickedId);
        message.put("success",ticket);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
