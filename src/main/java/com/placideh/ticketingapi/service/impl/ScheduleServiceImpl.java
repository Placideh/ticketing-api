package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.utils.Utils;
import com.placideh.ticketingapi.entity.Schedule;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.ScheduleRepo;
import com.placideh.ticketingapi.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final Utils utils;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, Utils utils) {
        this.scheduleRepo = scheduleRepo;
        this.utils = utils;
    }

    @Override
    public Schedule createSchedule(ScheduleDto scheduleDto) {
        log.info("creating the schedule");

        Schedule schedule=utils.convertScheduleDtoToSchedule(scheduleDto);

        return scheduleRepo.save(schedule);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepo.findAll();
    }

    @Override
    public List<Schedule> getSchedulesByDate(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, dateFormatter);
        return scheduleRepo.findByDate( date)
                .orElseThrow(()-> new NotFoundException("Date for schedule provided not found"));
    }

    @Override
    public Schedule getScheduleByDateAndTime(ScheduleDto scheduleDto) {

        Schedule schedule=utils.convertScheduleDtoToSchedule(scheduleDto);
        return scheduleRepo.findScheduleByDateAndTime(schedule.getStartTime(),schedule.getEndTime(),schedule.getDate())
                .orElseThrow(()-> new NotFoundException("Date| startTime| endTime for schedule provided not found"));
    }
}
