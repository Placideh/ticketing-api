package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.Dto.TripDto;
import com.placideh.ticketingapi.entity.Trip;
import com.placideh.ticketingapi.service.TripService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/trips")
@Api(value = "Trip endpoints")
public class TripController {

    private final TripService tripService;


    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/")
    @ApiOperation(value="generate Ticket")
    public ResponseEntity<Map<String, Trip>> createTrip(TripDto tripDto){

        Map<String,Trip> message=new HashMap<>();
        Trip createdTrip=tripService.createTrip(tripDto);
        message.put("success",createdTrip);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ApiOperation(value="get all trip")
    public ResponseEntity<Map<String, List<Trip>>> getTrips(){
        Map<String,List< Trip>> message=new HashMap<>();
        List< Trip> trips= tripService.getTrips();
        message.put("success",trips);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{plateNumber}")
    @ApiOperation(value="get  Trip")
    public ResponseEntity<Map<String, Trip>> getTripByBus(String plateNumber){

        Map<String,Trip> message=new HashMap<>();
        Trip trip=tripService.getTripByBus(plateNumber);
        message.put("success",trip);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/schedule")
    @ApiOperation(value="get Trip")
    public ResponseEntity<Map<String, Trip>> getTripBySchedule(String source, String destination, ScheduleDto scheduleDto){

        Map<String,Trip> message=new HashMap<>();
        Trip trip=tripService.getTripBySchedule(source,destination,scheduleDto);
        message.put("success",trip);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }


}
