package com.poc.byondmap.service;

import com.poc.byondmap.model.Otp;
import com.poc.byondmap.repository.OtpRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Service class for handling OTP (One-Time Password) generation and validation.
 * Provides functionality for generating and validating OTPs for users.
 */
@Service
@AllArgsConstructor
public class OtpService {

    @Autowired
    private final OtpRepository otpRepository;
    @Autowired
    private final SmsService smsService;

    /**
     * Generates an OTP for a given phone number and saves it in the database.
     * The OTP is sent to the user's phone number via SMS and is valid for 5 minutes.
     *
     * @param phoneNumber The phone number for which the OTP is generated.
     * @return The generated OTP.
     */
    public String generateOtp(String phoneNumber) {
        String otp = String.valueOf(new Random().nextInt(999999));
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        Otp otpEntity = new Otp();
        otpEntity.setPhoneNumber(phoneNumber);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(expiryTime);

        otpRepository.save(otpEntity);
        // Send the OTP via SMS
        String message = "Welcome to Byond Map.\nYour OTP is: " + otp + ". It is valid for 5 minutes.";
        smsService.sendSms(phoneNumber, message);

        return otp;

    }

    /**
     * Validates the OTP for a given phone number.
     * Checks if the provided OTP matches the one stored in the database
     * and whether it has expired.
     *
     * @param phoneNumber The phone number for which the OTP is validated.
     * @param otp The OTP to be validated.
     * @return {@code true} if the OTP is valid and not expired; {@code false} otherwise.
     */
    public boolean validateOtp(String phoneNumber, String otp) {
        Otp otpEntity = otpRepository.findByPhoneNumber(phoneNumber);
        if (otpEntity != null && otpEntity.getOtp().equals(otp)) {
            return LocalDateTime.now().isBefore(otpEntity.getExpiryTime());
        }
        return false;
    }
}

