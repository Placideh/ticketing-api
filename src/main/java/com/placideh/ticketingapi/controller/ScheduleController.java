package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Schedule;
import com.placideh.ticketingapi.service.ScheduleService;
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
@RequestMapping("/api/v1/schedules")
@SecurityRequirement(name = "bearerAuth")
public class ScheduleController {

    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Schedule>> createSchedule(@Valid @RequestBody ScheduleDto scheduleDto){
        Map<String,Schedule> message=new HashMap<>();
        Schedule createdSchedule= scheduleService.createSchedule(scheduleDto);
        message.put("success",createdSchedule);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, List< Schedule>>> getSchedules(){
        Map<String,List< Schedule>> message=new HashMap<>();
        List< Schedule> schedules= scheduleService.getSchedules();
        message.put("success",schedules);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/view-schedule")
    public ResponseEntity<Map<String,  Schedule>> getScheduleByDateAndTime(@Valid @RequestBody ScheduleDto scheduleDto){
        Map<String,Schedule> message=new HashMap<>();
        Schedule schedule= scheduleService.getScheduleByDateAndTime(scheduleDto);
        message.put("success",schedule);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{date}")
    public ResponseEntity<Map<String, List< Schedule>>> getSchedulesByDate(@PathVariable String date){
        Map<String,List< Schedule>> message=new HashMap<>();
        List< Schedule> schedules= scheduleService.getSchedulesByDate(date);
        message.put("success",schedules);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
