package com.placideh.ticketingapi.service.impl;


import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.Dto.TripDto;
import com.placideh.ticketingapi.entity.*;
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

    private final ScheduleService service;
    private final BusService busService;

    public TripServiceImpl(BusRepo busRepo, TripRepo tripRepo, RouteRepo routeRepo,
                           ScheduleServiceImpl service, BusService busService) {
        this.busRepo = busRepo;
        this.tripRepo = tripRepo;
        this.routeRepo = routeRepo;
        this.service = service;
        this.busService = busService;
    }

    @Override
    public Trip createTrip(TripDto trip) {

        log.info("creating the trip");

        Bus  bus=busRepo.findByPlateNumber(trip.getPlateNumber())
                .orElseThrow(()-> new NotFoundException("Bus PlateNumber: "+trip.getPlateNumber()+" not found"));

        //check if  the trip is forward or reverse
        Route sourceRoute= routeRepo.findByName(trip.getSource())
                .orElseThrow(()-> new NotFoundException("Source Route: "+trip.getSource()+" not found"));

        Route destinationRoute= routeRepo.findByName(trip.getDestination())
                .orElseThrow(()-> new NotFoundException("Destination Route: "+trip.getDestination()+" not found"));

        Schedule schedule=service.getScheduleByDateAndTime(trip.getSchedule());

//        TODO: CHECK ALL VALIDATION REGARDING CREATING THE TRIP HERE
        Trip newTrip;
        if (sourceRoute.getRouteNumber()< destinationRoute.getRouteNumber()) {
            log.info("saving the trip In Forward");
            newTrip=Trip.builder()
                    .source(sourceRoute)
                    .destination(destinationRoute)
                    .bus(bus)
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
        Route sourceRoute= routeRepo.findByName(source)
                .orElseThrow(()-> new NotFoundException("Source Route: "+source+" not found"));

        Route destinationRoute= routeRepo.findByName(destination)
                .orElseThrow(()-> new NotFoundException("Destination Route: "+destination+" not found"));
        Schedule schedule=service.getScheduleByDateAndTime(scheduleDto);

        return tripRepo.findBySourceAndDestinationAndSchedule(sourceRoute,destinationRoute,schedule);
    }
//    TODO IMPLEMENT ALSO GETTING ALL POSSIBLE SCHEDULES BY DATE
}
