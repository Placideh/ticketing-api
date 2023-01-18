package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordReset {
    @NotBlank
    @NotNull
    @Size(min=6,max=6,message = "otp must be 6 characters long")
    private String otp;
    @NotBlank
    @NotNull
    @Size(min=8,max=200,message = "password must be 8 characters or more")
    private String password;
    @NotBlank
    @NotNull
    @Size(min=8,max=200,message = "password must be 8 characters or more")
    private String confirmPassword;
}
