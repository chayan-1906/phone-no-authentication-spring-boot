package com.padmanabhasmac.phonenoauth.phonenoauthentication.services;

import com.padmanabhasmac.phonenoauth.phonenoauthentication.dto.OTPStatus;
import com.padmanabhasmac.phonenoauth.phonenoauthentication.dto.PasswordResetRequestDTO;
import com.padmanabhasmac.phonenoauth.phonenoauthentication.dto.PasswordResetResponseDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {

    Map<String, String> otpMap = new HashMap<>();

    public Mono<PasswordResetResponseDTO> sendOTPPasswordReset(PasswordResetRequestDTO passwordResetRequestDTO) {
        PasswordResetResponseDTO passwordResetResponseDTO;
        try {
            String otp = generateOTP();
            String otpMessage = "Dear Customer, Your OTP is " + otp + ". Use this Passcode to complete your transaction. Thank You!";
            otpMap.put(passwordResetRequestDTO.getUserName(), otp);
            passwordResetResponseDTO = new PasswordResetResponseDTO(OTPStatus.DELIVERED, otpMessage);
        } catch (Exception ex) {
            passwordResetResponseDTO = new PasswordResetResponseDTO(OTPStatus.FAILED, ex.getMessage());
        }
        return Mono.just(passwordResetResponseDTO);
    }

    public Mono<String> validateOTP(String userInputOtp, String userName) {
        if (userInputOtp.equals(otpMap.get(userName))) {
            otpMap.remove(userName, userInputOtp);
            return Mono.just("Valid OTP, please proceed with your transaction");
        } else {
            return Mono.error(new IllegalArgumentException("Invalid OTP, please try again!"));
        }
    }

    // 6 digit otp
    private String generateOTP() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
