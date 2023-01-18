package com.placideh.ticketingapi.Dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
@NotNull
public class UserDto {
    @NotBlank(message = "first name can not be blank")
    @NotEmpty(message = "first name must be entered")
    private String firstName;

    @NotBlank(message = "last name can not be blank")
    @NotEmpty(message = "last name must be entered")
    private String lastName;

    @NotBlank(message = "password name can not be blank")
    @NotEmpty(message = "password must be entered")
    @Size(min = 8,max = 200,message = "password must be 8 characters or more")
    private String password;

    @Email(message = "enter a valid email")
    @NotBlank(message = "email can not be blank")
    @NotEmpty(message = "email must be entered")
    private String email;
}
