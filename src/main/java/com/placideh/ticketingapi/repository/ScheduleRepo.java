package com.placideh.ticketingapi.repository;

import com.placideh.ticketingapi.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule,Integer> {
   Optional<List<Schedule>>  findByDate(LocalDate date);


   @Query(
           value="SELECT * FROM schedules WHERE start_time=?1 AND end_time=?2 AND date=?3  ",
           nativeQuery=true
   )
   Optional<Schedule> findScheduleByDateAndTime(LocalDateTime starTime,LocalDateTime endTime, LocalDate date);


}
