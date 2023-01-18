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
    @Column(name="source")
    private Route source;
    @OneToOne
    @Column(name="destination")
    private Route destination;



    @OneToOne
    private Bus bus;

    @OneToOne
    private Schedule schedule;
    @Column(name="allocated_seats")
    private Integer allocatedSeats=0; //setting zero as initial value on create

    @Column(name="remaining_seats")
    private Integer remainingSeats=0; //setting zero as initial value on create
    @Column(name = "trip_status")
    private Status status; // status either FORWARD OR BACKWARD
}
