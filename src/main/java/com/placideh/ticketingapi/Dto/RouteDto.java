package com.placideh.ticketingapi.Dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NotNull
public class RouteDto {
    @NotBlank(message = "Route Name can not be blank")
    @NotEmpty(message = "Route Name must be entered")
    private String name;

    @NotBlank(message = "Route Status can not be blank")
    @NotEmpty(message = "Route Status must be entered")
    private String status;
}
