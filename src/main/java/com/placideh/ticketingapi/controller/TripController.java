package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.Dto.TripDto;
import com.placideh.ticketingapi.entity.Trip;
import com.placideh.ticketingapi.service.TripService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/trips")
@SecurityRequirement(name = "bearerAuth")
public class TripController {

    private final TripService tripService;


    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Trip>> createTrip(@Valid @RequestBody TripDto tripDto){

        Map<String,Trip> message=new HashMap<>();
        Trip createdTrip=tripService.createTrip(tripDto);
        message.put("success",createdTrip);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, List<Trip>>> getTrips(){
        Map<String,List< Trip>> message=new HashMap<>();
        List< Trip> trips= tripService.getTrips();
        message.put("success",trips);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{plateNumber}")
    public ResponseEntity<Map<String, Trip>> getTripByBus(@PathVariable String plateNumber){

        Map<String,Trip> message=new HashMap<>();
        Trip trip=tripService.getTripByBus(plateNumber);
        message.put("success",trip);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Map<String, Trip>> getTripById(@PathVariable Integer tripId){

        Map<String,Trip> message=new HashMap<>();
        Trip trip=tripService.getTripById(tripId);
        message.put("success",trip);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping("/schedule")
    public ResponseEntity<Map<String, Trip>> getTripBySchedule(@RequestParam String source,@RequestParam String destination, @Valid @RequestBody ScheduleDto scheduleDto){

        Map<String,Trip> message=new HashMap<>();
        Trip trip=tripService.getTripBySchedule(source,destination,scheduleDto);
        message.put("success",trip);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
