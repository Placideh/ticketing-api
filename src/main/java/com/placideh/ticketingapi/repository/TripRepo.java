package com.placideh.ticketingapi.repository;

import com.placideh.ticketingapi.entity.Bus;
import com.placideh.ticketingapi.entity.Route;
import com.placideh.ticketingapi.entity.Schedule;
import com.placideh.ticketingapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TripRepo extends JpaRepository<Trip,Integer> {
    Trip findByBus(Bus bus);

    Trip findBySourceAndDestinationAndSchedule(Route sourceRoute, Route destinationRoute, Schedule schedule);
}
