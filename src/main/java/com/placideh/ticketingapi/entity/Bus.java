package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "buses",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "plateNumber_unique",
                        columnNames = "plate_number"
                )
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bus_id")
    private Integer id;
    @Column(name = "plate_number")
    @NotBlank(message = "plateNumber can not be blank")
    @NotEmpty(message = "plateNumber must be entered")
    private String plateNumber;

    @Column(name = "seat_size")
    @NotBlank(message = "Bus seatSize can not be blank")
    @NotEmpty(message = "Bus seatSize must be entered")
    private Integer seatSize;

}
