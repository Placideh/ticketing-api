package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.Dto.TripDto;
import com.placideh.ticketingapi.entity.Trip;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripService {
    Trip createTrip(TripDto trip);

    List<Trip> getTrips();


    Trip getTripByBus(String plateNumber);

    Trip updateTrip(Trip trip);

    Trip getTripBySchedule(String source, String destination, ScheduleDto scheduleDto);

}
