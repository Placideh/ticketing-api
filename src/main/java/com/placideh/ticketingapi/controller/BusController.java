package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.entity.Bus;
import com.placideh.ticketingapi.service.BusService;
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
@RequestMapping("/api/v1/buses")
@SecurityRequirement(name = "bearerAuth")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping( value = "/create")
    public ResponseEntity<Map<String,Bus>> registerBus(@Valid @RequestBody Bus bus) {
        Map<String ,Bus> message=new HashMap<>();
        Bus newBus;
        try {
            newBus = busService.createBus(bus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        message.put("success",newBus);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<Map<String, List<Bus>>> getBuses(){
        Map<String ,List<Bus>> message=new HashMap<>();
        List<Bus> buses=busService.getBuses();
        message.put("message",buses);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/{plateNumber}")
    public ResponseEntity<Map<String, Bus>> getBusByPlateNumber(@PathVariable String plateNumber){
        Map<String ,Bus> message=new HashMap<>();
        Bus bus=busService.getBusByPlateNumber(plateNumber);
        message.put("message",bus);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/{plateNumber}")
    public ResponseEntity updateBusByPlateNumber(@PathVariable String plateNumber,Integer seats){
        busService.updateBusByPlateNumber(plateNumber,seats);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
