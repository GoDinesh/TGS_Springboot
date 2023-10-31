package com.tgsbhadohi.TGS.controller.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgsbhadohi.TGS.entities.sms.SmsRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequestMapping("/sms")
public class SmsController {
	@Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone.number}")
    private String TWILIO_PHONE_NUMBER;

 @PostMapping("/send-sms/insert")
   public ResponseEntity<?> sendSMS(@RequestBody SmsRequest request) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
            new PhoneNumber(request.getTo()),
            new PhoneNumber(TWILIO_PHONE_NUMBER),
            request.getBody()
        ).create();

   return ResponseEntity.ok().body(message.getSid());
}

    }
