package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.entity.Bus;
import com.placideh.ticketingapi.service.BusService;
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
@RequestMapping("/api/v1/buses")
@Api(value = "Bus endpoints")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping( value = "/create")
    @ApiOperation(value = "register a bus")
    public ResponseEntity<Map<String,Bus>> registerBus(Bus bus ) {
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
    @ApiOperation(value = "get all buses")
    public ResponseEntity<Map<String, List<Bus>>> getBuses(){
        Map<String ,List<Bus>> message=new HashMap<>();
        List<Bus> buses=busService.getBuses();
        message.put("message",buses);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/{plateNumber}")
    @ApiOperation(value = "get bus")
    public ResponseEntity<Map<String, Bus>> getBusByPlateNumber(@PathVariable String plateNumber){
        Map<String ,Bus> message=new HashMap<>();
        Bus bus=busService.getBusByPlateNumber(plateNumber);
        message.put("message",bus);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PutMapping("/{plateNumber}")
    @ApiOperation(value = "update bus ")
    public ResponseEntity<Map<String, Bus>> updateBusByPlateNumber(@PathVariable String plateNumber,Integer seats){
        Map<String ,Bus> message=new HashMap<>();
        Bus bus=busService.updateBusByPlateNumber(plateNumber,seats);
        message.put("message",bus);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
