package com.placideh.ticketingapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_id")
    private Integer id;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Trip trip;





}
