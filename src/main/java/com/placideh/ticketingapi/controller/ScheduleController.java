package com.placideh.ticketingapi.controller;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Route;
import com.placideh.ticketingapi.entity.Schedule;
import com.placideh.ticketingapi.service.ScheduleService;
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
@RequestMapping("/api/v1/schedules")
@Api(value = "Route endpoints")
public class ScheduleController {

    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/")
    @ApiOperation(value="create schedule")
    public ResponseEntity<Map<String, Schedule>> createSchedule(ScheduleDto scheduleDto){
        Map<String,Schedule> message=new HashMap<>();
        Schedule createdSchedule= scheduleService.createSchedule(scheduleDto);
        message.put("success",createdSchedule);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/")
    @ApiOperation(value="get all schedule")
    public ResponseEntity<Map<String, List< Schedule>>> getSchedules(){
        Map<String,List< Schedule>> message=new HashMap<>();
        List< Schedule> schedules= scheduleService.getSchedules();
        message.put("success",schedules);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/schedule")
    @ApiOperation(value="get schedule")
    public ResponseEntity<Map<String,  Schedule>> getScheduleByDateAndTime(ScheduleDto scheduleDto){
        Map<String,Schedule> message=new HashMap<>();
        Schedule schedule= scheduleService.getScheduleByDateAndTime(scheduleDto);
        message.put("success",schedule);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/{date}")
    @ApiOperation(value="get all schedule")
    public ResponseEntity<Map<String, List< Schedule>>> getSchedulesByDate(@PathVariable String date){
        Map<String,List< Schedule>> message=new HashMap<>();
        List< Schedule> schedules= scheduleService.getSchedulesByDate(date);
        message.put("success",schedules);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
