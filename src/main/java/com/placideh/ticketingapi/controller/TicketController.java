package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.entity.Ticket;
import com.placideh.ticketingapi.service.TicketService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/tickets")
@SecurityRequirement(name = "bearerAuth")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Ticket>> generateTicket(@RequestParam String userEmail,@RequestParam Integer tripId){

        Map<String,Ticket> message=new HashMap<>();
        Ticket generatedTicket= ticketService.generateTicket(userEmail,tripId);
        message.put("success",generatedTicket);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<Map<String, Ticket>> getTicketById(Integer tickedId){

        Map<String,Ticket> message=new HashMap<>();
        Ticket ticket= ticketService.getTicketById(tickedId);
        message.put("success",ticket);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Ticket>>> getGeneratedTickets(){

        Map<String,List<Ticket>> message=new HashMap<>();
        List<Ticket> generatedTickets= ticketService.getGeneratedTickets();
        message.put("success",generatedTickets);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
