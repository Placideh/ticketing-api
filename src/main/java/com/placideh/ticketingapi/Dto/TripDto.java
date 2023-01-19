package com.placideh.ticketingapi.Dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
@NotNull
public class TripDto {
    @NotBlank(message = "Trip source can not be blank")
    @NotEmpty(message = "Trip source must be entered")
    private String source;
    @NotBlank(message = "destination can not be blank")
    @NotEmpty(message = "destination must be entered")
    private String destination;

    @NotBlank(message = "plateNumber name can not be blank")
    @NotEmpty(message = "plateNumber must be entered")
    private String plateNumber;

    private ScheduleDto schedule;

    @NotBlank(message = "routeStops name can not be blank")
    @NotEmpty(message = "routeStops must be entered")
    @Min(50)
    private String routeStops;



}
