package com.placideh.ticketingapi.service.impl;


import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.Dto.TripDto;
import com.placideh.ticketingapi.entity.*;
import com.placideh.ticketingapi.exception.CustomInputException;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.*;
import com.placideh.ticketingapi.service.BusService;
import com.placideh.ticketingapi.service.ScheduleService;
import com.placideh.ticketingapi.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TripServiceImpl implements TripService {

    private final BusRepo busRepo;

    private final TripRepo tripRepo;
    private final RouteRepo routeRepo;

    private final ScheduleService scheduleService;
    private final BusService busService;

    private final Integer ZERO_SEAT_VALUE=0;

    public TripServiceImpl(BusRepo busRepo, TripRepo tripRepo, RouteRepo routeRepo,
                           ScheduleService scheduleService, BusService busService) {
        this.busRepo = busRepo;
        this.tripRepo = tripRepo;
        this.routeRepo = routeRepo;
        this.scheduleService = scheduleService;
        this.busService = busService;
    }

    @Override
    public Trip createTrip(TripDto trip) {

        log.info("creating the trip");

        Bus  bus=busRepo.findByPlateNumber(trip.getPlateNumber().trim().toUpperCase())
                .orElseThrow(()-> new NotFoundException("Bus PlateNumber: "+trip.getPlateNumber()+" not found"));

        //check if  the trip is forward or reverse
        Route sourceRoute= routeRepo.findByName(trip.getSource().trim().toUpperCase())
                .orElseThrow(()-> new NotFoundException("Source Route: "+trip.getSource()+" not found"));

        Route destinationRoute= routeRepo.findByName(trip.getDestination().trim().toUpperCase())
                .orElseThrow(()-> new NotFoundException("Destination Route: "+trip.getDestination()+" not found"));

        Schedule schedule=scheduleService.getScheduleByDateAndTime(trip.getSchedule());

        //check if the vehicle  for a trip isn't booked on that schedule
        Trip existingTrip=getTripByBus(bus.getPlateNumber());

        if (existingTrip.getSchedule().equals(schedule)) throw new CustomInputException("The Bus selected is already occupied on that time bound");


//        TODO: CHECK ALL VALIDATION REGARDING CREATING THE TRIP HERE
        Trip newTrip;
        if (sourceRoute.getRouteNumber()< destinationRoute.getRouteNumber()) {
            log.info("saving the trip In Forward");
            newTrip=Trip.builder()
                    .source(sourceRoute)
                    .destination(destinationRoute)
                    .bus(bus)
                    .allocatedSeats(ZERO_SEAT_VALUE)
                    .remainingSeats(bus.getSeatSize())
                    .status(Status.FORWARD)
                    .schedule(schedule)
                    .build();

           return  tripRepo.save(newTrip);
        }
    log.info("saving the trip In Reverse");
        newTrip=Trip.builder()
                .source(sourceRoute)
                .destination(destinationRoute)
                .bus(bus)
                .allocatedSeats(ZERO_SEAT_VALUE)
                .remainingSeats(bus.getSeatSize())
                .status(Status.BACKWARD)
                .schedule(schedule)
                .build();

        return  tripRepo.save(newTrip);
    }

    @Override
    public List<Trip> getTrips() {
        return tripRepo.findAll();
    }

    @Override
    public Trip getTripByBus(String plateNumber) {
        Bus bus=busService.getBusByPlateNumber(plateNumber);
        return tripRepo.findByBus(bus);
    }

    @Override
    public Trip updateTrip(Trip trip) {

        return tripRepo.save(trip);
    }

    @Override
    public Trip getTripBySchedule(String source, String destination, ScheduleDto scheduleDto) {
        Route sourceRoute= routeRepo.findByName(source.trim().toUpperCase())
                .orElseThrow(()-> new NotFoundException("Source Route: "+source+" not found"));

        Route destinationRoute= routeRepo.findByName(destination.trim().toUpperCase())
                .orElseThrow(()-> new NotFoundException("Destination Route: "+destination+" not found"));
        Schedule schedule=scheduleService.getScheduleByDateAndTime(scheduleDto);

        return tripRepo.findBySourceAndDestinationAndSchedule(sourceRoute,destinationRoute,schedule);
    }

    @Override
    public Trip getTripById(Integer tripId) {
        return tripRepo.findById(tripId)
                .orElseThrow(()-> new NotFoundException("TripId: "+tripId+" not found"));
    }
}
