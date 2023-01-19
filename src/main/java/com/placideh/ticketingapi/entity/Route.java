package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "routes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="route_id")
    private Integer id;

    @Column(name = "route_name")
    @NotBlank(message = "Route Name can not be blank")
    @NotEmpty(message = "Route Name must be entered")
    private String name;


    @Column(name = "route_number")
    private Integer routeNumber;

    @Column(name = "status")
    private Status status;

    private static int nextId=100;

    @PrePersist
    public void generateSequentialId(){
        routeNumber=nextId++;
    }
}
