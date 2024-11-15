package com.poc.byondmap.controller;

import com.poc.byondmap.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle OTP (One-Time Password) generation and validation.
 * Provides endpoints to generate and validate OTP for phone numbers.
 */
@RestController
@RequestMapping("/api/otp")
public class OtpController {
    private final OtpService otpService;

    /**
     * Constructor to initialize the OtpController with the required OtpService.
     *
     * @param otpService The OtpService instance used for OTP generation and validation.
     */
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    /**
     * Generates an OTP for the provided phone number.
     * Sends a generated OTP to the specified phone number.
     *
     * @param phoneNumber The phone number for which the OTP is generated.
     * @return A ResponseEntity containing the OTP status message.
     */
    @PostMapping("/generate")
    public ResponseEntity<String> generateOtp(@RequestParam String phoneNumber) {
        String otp = otpService.generateOtp(phoneNumber);
        return ResponseEntity.ok("OTP sent: " + otp);
    }

    /**
     * Validates the OTP provided for a specific phone number.
     * Verifies if the OTP matches the one generated for the given phone number.
     *
     * @param phoneNumber The phone number for which the OTP is validated.
     * @param otp The OTP to validate.
     * @return A ResponseEntity containing the validation result message.
     */
    @PostMapping("/validate")
    public ResponseEntity<String> validateOtp(
            @RequestParam String phoneNumber,
            @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(phoneNumber, otp);
        if (isValid) {
            return ResponseEntity.ok("OTP is valid");
        } else {
            return ResponseEntity.status(400).body("Invalid OTP or expired");
        }
    }
}
