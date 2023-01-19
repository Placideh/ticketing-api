package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="schedule_id")
    private Integer id;
    @Column(name="start_time")
    private LocalTime startTime;
    @Column(name="end_time")
    private LocalTime endTime;
    @Column(name="date")
    private LocalDateTime date;


}
