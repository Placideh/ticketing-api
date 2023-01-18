package com.placideh.ticketingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="otps_table")
@Builder
public class OneTimePassword {
    @Id
    private String otp;

    private Status status;

  @OneToOne
    private User user;

    private LocalDateTime issuedDateAndTime;

}
