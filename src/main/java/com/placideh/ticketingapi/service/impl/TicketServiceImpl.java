package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.*;
import com.placideh.ticketingapi.exception.CustomInputException;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.TicketRepo;
import com.placideh.ticketingapi.repository.TripRepo;
import com.placideh.ticketingapi.repository.UserRepo;
import com.placideh.ticketingapi.service.TicketService;
import com.placideh.ticketingapi.service.TripService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final TripService tripService;

    private final UserRepo userRepo;

    private final String USER_STATUS="VERIFIED";

    private final Integer INITIAL_VALUE=1;
    private final Integer ZERO_VALUE=0;


    public TicketServiceImpl(TicketRepo ticketRepo,  TripService tripService, UserRepo userRepo) {
        this.ticketRepo = ticketRepo;
        this.tripService = tripService;
        this.userRepo = userRepo;
    }



    @Override
    public Ticket generateTicket(String userEmail,Integer tripId) {
        // Create a ScheduledExecutorService to run the sleep task
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Retrieve data
        User user=userRepo.findByEmail(userEmail)
                .orElseThrow(()-> new NotFoundException("User With Email: "+userEmail+" not found"));
        // this could be catched on JWT LOGIN
        if (!user.getStatus().name().equals(USER_STATUS)) throw new CustomInputException("User With Email:"+userEmail+" Is not Verified");

        Trip trip=tripService.getTripById(tripId);

        if(trip.getSchedule().getDate().isBefore(LocalDateTime.now())) throw new CustomInputException("You can only generate a ticket from the present time");



        if((Objects.equals(trip.getBus().getSeatSize(), trip.getAllocatedSeats()))) throw new CustomInputException("The Bus Is Already Full");

        // Schedule a sleep task to run concurrently with the data retrieval and insertion
        executor.schedule(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, TimeUnit.MILLISECONDS);

        if((trip.getBus().getSeatSize() >= trip.getAllocatedSeats()) && trip.getRemainingSeats()>ZERO_VALUE){
            // allocate a seat on the trip

            Integer newSeat=trip.getAllocatedSeats()+INITIAL_VALUE;

            Integer remainingSeats=trip.getRemainingSeats()-INITIAL_VALUE;

            trip.setAllocatedSeats(newSeat);
            trip.setRemainingSeats(remainingSeats);

            tripService.updateTrip(trip);
        }

        // generate the ticket

        Ticket ticket=Ticket.builder()
                .user(user)
                .trip(trip)
                .build();


        return ticketRepo.save(ticket);
    }

    @Override
    public Ticket getTicketById(Integer ticketId) {
        return ticketRepo.findById(ticketId)
                .orElseThrow(()-> new NotFoundException("ticketId: "+ticketId+" not found"));

    }

    @Override
    public List<Ticket> getGeneratedTickets() {
        return ticketRepo.findAll();
    }
}
