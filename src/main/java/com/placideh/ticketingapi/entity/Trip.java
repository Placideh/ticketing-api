package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trips")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trip_id")
    private Integer id;

    @OneToOne
    private Route route;

    @OneToOne
    private Bus bus;

    @OneToOne
    private Schedule schedule;
    @Column(name="allocated_seats")
    private Integer allocatedSeats;
    @Column(name="remaining_seats")
    private Integer remainingSeats;
}
