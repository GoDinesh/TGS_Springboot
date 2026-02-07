package com.tgsbhadohi.TGS.service.authorization;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class OtpService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    private final Map<String, String> otpStore = new HashMap<>();

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public void sendOtp(String phone) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStore.put(phone, otp);

        Message.creator(
                new PhoneNumber(phone),
                new PhoneNumber(fromNumber),
                "Your OTP is: " + otp + ". Valid for 5 minutes."
        ).create();
    }

    public boolean verifyOtp(String phone, String otp) {
    	System.out.print(otpStore.get(phone));
        return otp.equals(otpStore.get(phone));
    }
}

