package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "routes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email"
                )
        })
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


    @SequenceGenerator(name = "route_seq", sequenceName = "route_seq", initialValue = 100, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    @Column(name = "route_number")
    @NotBlank(message = "Route Number can not be blank")
    @NotEmpty(message = "Route Number must be entered")
    private Integer routeNumber;

    @Column(name = "status")
    @NotBlank(message = "Route Status  can not be blank")
    @NotEmpty(message = "Route Status must be entered")
    private Status status;
}
