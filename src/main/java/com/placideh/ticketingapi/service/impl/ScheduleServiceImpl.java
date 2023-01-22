package com.placideh.ticketingapi.service.impl;

import com.placideh.ticketingapi.Dto.ScheduleDto;
import com.placideh.ticketingapi.exception.CustomInputException;
import com.placideh.ticketingapi.utils.CustomDateTimeConverter;
import com.placideh.ticketingapi.entity.Schedule;
import com.placideh.ticketingapi.exception.NotFoundException;
import com.placideh.ticketingapi.repository.ScheduleRepo;
import com.placideh.ticketingapi.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final CustomDateTimeConverter customDateTimeConverter;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, CustomDateTimeConverter customDateTimeConverter) {
        this.scheduleRepo = scheduleRepo;
        this.customDateTimeConverter = customDateTimeConverter;
    }

    @Override
    public Schedule createSchedule(ScheduleDto scheduleDto) {
        log.info("creating the schedule");
        String currentDate=LocalDate.now().toString();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDate.parse(currentDate, dateFormatter).atStartOfDay();

        Schedule schedule=customDateTimeConverter.convertScheduleDtoToSchedule(scheduleDto);

        if(schedule.getDate().isBefore(date)) throw new CustomInputException("You can only generate a schedule starting from the present time");

        if((schedule.getDate().equals(date)) && schedule.getStartTime().isAfter(LocalTime.now()))
            throw new CustomInputException("You can only schedule start time which is not after present time ");

        if ((schedule.getDate().equals(date)) && schedule.getStartTime().isAfter(schedule.getEndTime()))
            throw new CustomInputException("You can only schedule start time which is not after ending time ");


        if ((schedule.getDate().isAfter(date)) && (schedule.getStartTime().isAfter(schedule.getEndTime())))
            throw new CustomInputException("You can only schedule start time which is not after ending time ");

        return scheduleRepo.save(schedule);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepo.findAll();
    }

    @Override
    public List<Schedule> getSchedulesByDate(String dateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        LocalDateTime date = LocalDate.parse(dateString, dateFormatter).atStartOfDay();
        return scheduleRepo.findByDate( date)
                .orElseThrow(()-> new NotFoundException("Date for schedule provided not found"));
    }

    @Override
    public Schedule getScheduleByDateAndTime(ScheduleDto scheduleDto) {

        Schedule schedule=customDateTimeConverter.convertScheduleDtoToSchedule(scheduleDto);
        return scheduleRepo.findScheduleByDateAndTime(schedule.getStartTime(),schedule.getEndTime(),schedule.getDate())
                .orElseThrow(()-> new NotFoundException("Date| startTime| endTime for schedule provided not found"));
    }
}
