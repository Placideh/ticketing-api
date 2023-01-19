package com.placideh.ticketingapi.utils;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.entity.Schedule;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CustomDateTimeConverter {

    public Schedule convertScheduleDtoToSchedule(ScheduleDto schedule){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        LocalTime startTime = LocalTime.parse(schedule.getStartTime());
        LocalTime endTime = LocalTime.parse(schedule.getEndTime());
        LocalDateTime date = LocalDate.parse(schedule.getDate(), dateFormatter).atStartOfDay();
        return Schedule.builder()
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .build();
    }
}
