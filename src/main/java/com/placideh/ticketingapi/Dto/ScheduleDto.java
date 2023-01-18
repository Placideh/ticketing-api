package com.placideh.ticketingapi.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NotNull
public class ScheduleDto {


    @NotBlank(message = "StartTime can not be blank")
    @NotEmpty(message = "Trip source must be entered")
    private String startTime;

    @NotBlank(message = "EndTime can not be blank")
    @NotEmpty(message = "EndTime source must be entered")
    private String endTime;
    @NotBlank(message = "date can not be blank")
    @NotEmpty(message = "date must be entered")
    private String date;
}
