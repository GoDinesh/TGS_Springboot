package com.tgsbhadohi.TGS.controller.authorization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.entities.sms.OtpRequest;
import com.tgsbhadohi.TGS.entities.sms.OtpVerifyRequest;
import com.tgsbhadohi.TGS.service.authorization.OtpService;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@RequestBody OtpRequest request) {
        otpService.sendOtp(request.getPhone());
        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerifyRequest request) {
        if (otpService.verifyOtp(request.getPhone(), request.getOtp())) {
            return ResponseEntity.ok("OTP verified");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid OTP");
    }
}

