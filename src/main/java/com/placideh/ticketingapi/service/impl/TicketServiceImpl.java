package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.*;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.TicketRepo;
import com.placideh.ticketingapi.repository.UserRepo;
import com.placideh.ticketingapi.service.TicketService;
import com.placideh.ticketingapi.service.TripService;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TicketServiceImpl implements TicketService {

    private final TicketRepo ticketRepo;
    private final TripService tripService;

    private final UserRepo userRepo;


    public TicketServiceImpl(TicketRepo ticketRepo,  TripService tripService, UserRepo userRepo) {
        this.ticketRepo = ticketRepo;
        this.tripService = tripService;
        this.userRepo = userRepo;
    }


//    TODO MORE CHECKING REGARDING GENERATING THE TICKET

    @Override
    public Ticket generateTicket(String userEmail, String source, String destination, ScheduleDto scheduleDto) {
        // Create a ScheduledExecutorService to run the sleep task
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        // Retrieve data
        User user=userRepo.findByEmail(userEmail)
                .orElseThrow(()-> new NotFoundException("User With Email: "+userEmail+" not found"));


        Trip trip=tripService.getTripBySchedule(source,destination,scheduleDto);
        // Schedule a sleep task to run concurrently with the data retrieval and insertion
        executor.schedule(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, TimeUnit.MILLISECONDS);

        if((trip.getBus().getSeatSize()> trip.getAllocatedSeats()) && trip.getRemainingSeats()>0){
            // allocate a seat on the trip

            Integer newSeat=trip.getAllocatedSeats()+1;

            trip.setAllocatedSeats(newSeat);

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
}
