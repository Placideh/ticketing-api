package com.placideh.ticketingapi.service;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface ScheduleService {
    Schedule createSchedule(ScheduleDto scheduleDto);
    List<Schedule> getSchedules();

    List<Schedule> getSchedulesByDate(String date);

    Schedule getScheduleByDateAndTime(ScheduleDto scheduleDto );
}
