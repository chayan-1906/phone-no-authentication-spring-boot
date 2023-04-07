package com.padmanabhasmac.phonenoauth.phonenoauthentication.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDTO {

    private String phoneNumber; // destination
    private String userName;
    private String oneTimePassword;
}
