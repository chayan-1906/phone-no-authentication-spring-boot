package com.padmanabhasmac.phonenoauth.phonenoauthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetResponseDTO {

    private OTPStatus status;
    private String message;
}
