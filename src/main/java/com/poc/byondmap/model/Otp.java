package com.poc.byondmap.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents an OTP (One-Time Password) entity in the system.
 * This entity is mapped to the "otp" table in the database.
 */
@Entity
@Table(name = "otp")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Otp {

    /**
     * The phone number associated with the OTP.
     * This is the unique identifier for each OTP record.
     */
    @Id
    private String phoneNumber;


    /**
     * The generated OTP for the corresponding phone number.
     * This is a one-time password used for user verification.
     */
    private String otp;

    /**
     * The expiration time of the OTP.
     * This represents the time at which the OTP is no longer valid.
     */
    private LocalDateTime expiryTime;
}
