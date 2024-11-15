package com.poc.byondmap.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import org.springframework.stereotype.Service;

/**
 * Service class responsible for sending SMS using Twilio API.
 * This class provides functionality to send SMS messages to users' phone numbers.
 */
@Service
public class SmsService {

    // Twilio credentials (should ideally be stored securely, not in code)
    private static final String ACCOUNT_SID = "dummy";
    private static final String AUTH_TOKEN = "dummy";
    private static final String TWILIO_PHONE_NUMBER = "dummy";
    private static final String INDIA_COUNTRY_CODE = "+91";

    /**
     * Constructor that initializes the Twilio API with the provided account SID and auth token.
     * This constructor is called when the service is instantiated.
     */
    public SmsService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * Sends an SMS message to a given phone number using the Twilio service.
     *
     * @param toPhoneNumber The recipient's phone number (without the country code).
     * @param message The message to be sent.
     */
    public void sendSms(String toPhoneNumber, String message) {
        Message.creator(
                new com.twilio.type.PhoneNumber(INDIA_COUNTRY_CODE + toPhoneNumber),
                new com.twilio.type.PhoneNumber(TWILIO_PHONE_NUMBER),
                message
        ).create();
    }
}
