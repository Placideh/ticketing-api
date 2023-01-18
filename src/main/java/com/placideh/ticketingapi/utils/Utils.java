package com.placideh.ticketingapi.utils;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Schedule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    public Schedule convertScheduleDtoToSchedule(ScheduleDto schedule){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startTime = LocalDateTime.parse(schedule.getStartTime(), timeFormatter);
        LocalDateTime endTime = LocalDateTime.parse(schedule.getEndTime(), timeFormatter);
        LocalDate date = LocalDate.parse(schedule.getDate(), dateFormatter);
        return Schedule.builder()
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .build();
    }
}
