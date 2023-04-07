package com.padmanabhasmac.phonenoauth.phonenoauthentication;

import com.padmanabhasmac.phonenoauth.phonenoauthentication.configurations.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhoneNoAuthenticationApplication {

    @Autowired
    private TwilioConfig twilioConfig;

    public static void main(String[] args) {
        SpringApplication.run(PhoneNoAuthenticationApplication.class, args);
    }

    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }
}
